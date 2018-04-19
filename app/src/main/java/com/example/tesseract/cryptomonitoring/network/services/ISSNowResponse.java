package com.example.tesseract.cryptomonitoring.network.services;

import com.example.tesseract.cryptomonitoring.network.model.ISSNow;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ISSNowResponse {
    @GET("/v1/satellites/25544")
    Call<ISSNow> getInfo();

}
