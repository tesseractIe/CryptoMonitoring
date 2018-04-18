package com.example.tesseract.cryptomonitoring.storage.temp;

import com.example.tesseract.cryptomonitoring.network.model.CompleteTicker;
import com.example.tesseract.cryptomonitoring.network.model.Market;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class TempStorage {

    private static ArrayDeque<CompleteTicker> statesBTC = new ArrayDeque<>();
    private static ArrayDeque<CompleteTicker> statesETH = new ArrayDeque<>();
    private static List<String> marketsListBTC = new ArrayList<>();
    private static List<String> marketsListETH = new ArrayList<>();

    public static void pushInBTCList(CompleteTicker ticker){
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
    }

    public static void pushInETHList(CompleteTicker ticker){
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
    }

    public static List<CompleteTicker> getBTCList(){
        return new ArrayList<>(statesBTC);
    }


    public static List<CompleteTicker> getETHList(){
        return new ArrayList<>(statesETH);
    }
}
