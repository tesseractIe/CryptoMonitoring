package com.example.tesseract.cryptomonitoring.network.services;

import com.example.tesseract.cryptomonitoring.network.model.CompleteTicker;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetBTSToUSD {
    @GET("/api/full/btc-usd")
    Call<CompleteTicker> btc_usd();

    @GET("/api/full/eth-usd")
    Call<CompleteTicker> eth_usd();

}
