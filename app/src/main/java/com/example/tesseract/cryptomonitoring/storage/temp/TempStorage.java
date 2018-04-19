package com.example.tesseract.cryptomonitoring.storage.temp;

import com.example.tesseract.cryptomonitoring.network.RetrofitFactory;
import com.example.tesseract.cryptomonitoring.network.model.CompleteTicker;
import com.example.tesseract.cryptomonitoring.network.model.Market;
import com.example.tesseract.cryptomonitoring.network.services.GetBTSToUSD;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TempStorage {

    private static TempUpdate updates;

    private static ArrayDeque<CompleteTicker> statesBTC = new ArrayDeque<>();
    private static ArrayDeque<CompleteTicker> statesETH = new ArrayDeque<>();
    private static List<String> marketsListBTC = new ArrayList<>();
    private static List<String> marketsListETH = new ArrayList<>();

    private static void pushInBTCList(CompleteTicker ticker){
        for(Market m:ticker.ticker.markets){
            if (!marketsListBTC.contains(m.market)){
                marketsListBTC.add(m.market);
            }
        }
        if(statesBTC.size()<10){
            statesBTC.push(ticker);
        }else{
            statesBTC.push(ticker);
            statesBTC.removeLast();
        }
        updates.updatedBTC(new ArrayList<>(statesBTC));
    }

    private static void pushInETHList(CompleteTicker ticker){
        for(Market m:ticker.ticker.markets){
            if (!marketsListETH.contains(m.market)){
                marketsListETH.add(m.market);
            }
        }
        if(statesETH.size()<10){
            statesETH.push(ticker);
        }else{
            statesETH.push(ticker);
            statesETH.removeLast();
        }
        updates.updatedETH(new ArrayList<>(statesETH));
    }

    public static void updateBTCData() {
        RetrofitFactory.sR(GetBTSToUSD.class).btc_usd().enqueue(new Callback<CompleteTicker>() {
            @Override
            public void onResponse(Call<CompleteTicker> call, Response<CompleteTicker> response) {
                if (response.body() != null) {
                    pushInBTCList(response.body());
                }
            }

            @Override
            public void onFailure(Call<CompleteTicker> call, Throwable t) {
                updates.errorMessage(t.getMessage());
            }
        });
    }

    public static void updateETHData() {
        RetrofitFactory.sR(GetBTSToUSD.class).eth_usd().enqueue(new Callback<CompleteTicker>() {
            @Override
            public void onResponse(Call<CompleteTicker> call, Response<CompleteTicker> response) {
                if (response.body() != null) {
                    pushInETHList(response.body());
                }
            }

            @Override
            public void onFailure(Call<CompleteTicker> call, Throwable t) {
                updates.errorMessage(t.getMessage());
            }
        });
    }


}
