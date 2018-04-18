package com.example.tesseract.cryptomonitoring.presentation.presenter;


import android.util.Log;

import com.example.tesseract.cryptomonitoring.network.RetrofitService;
import com.example.tesseract.cryptomonitoring.network.model.CompleteTicker;
import com.example.tesseract.cryptomonitoring.network.services.GetBTSToUSD;
import com.example.tesseract.cryptomonitoring.presentation.view.MonitorView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@InjectViewState
public class MonitorPresenter extends MvpPresenter<MonitorView> {

    public void init() {
        RetrofitService.sR(GetBTSToUSD.class).btc_usd().enqueue(new Callback<CompleteTicker>() {
            @Override
            public void onResponse(Call<CompleteTicker> call, Response<CompleteTicker> response) {
                if (response.body() != null) {
                    Log.e("Tag::::", response.body().timestamp.toString());
                }
            }

            @Override
            public void onFailure(Call<CompleteTicker> call, Throwable t) {

            }
        });
    }

    public void getBTCData() {
        RetrofitService.sR(GetBTSToUSD.class).btc_usd().enqueue(new Callback<CompleteTicker>() {
            @Override
            public void onResponse(Call<CompleteTicker> call, Response<CompleteTicker> response) {
                if (response.body() != null) {
                    Log.e("Tag::::", response.body().timestamp.toString());
                }
            }

            @Override
            public void onFailure(Call<CompleteTicker> call, Throwable t) {
                getViewState().setErrorMessage(t.getMessage());
            }
        });
    }

    public void getETHData() {
        RetrofitService.sR(GetBTSToUSD.class).eth_usd().enqueue(new Callback<CompleteTicker>() {
            @Override
            public void onResponse(Call<CompleteTicker> call, Response<CompleteTicker> response) {
                if (response.body() != null) {
                    Log.e("Tag::::", response.body().timestamp.toString());
                }
            }

            @Override
            public void onFailure(Call<CompleteTicker> call, Throwable t) {
                getViewState().setErrorMessage(t.getMessage());
            }
        });
    }

}
