package com.example.tesseract.cryptomonitoring.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private static final String URL = "https://api.cryptonator.com";

    public static <T> T sR(final Class<T> clazz) {
        final Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL)
                .build();
        return retrofit.create(clazz);
    }
}
