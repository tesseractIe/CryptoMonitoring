package com.example.tesseract.cryptomonitoring.network;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.tesseract.cryptomonitoring.network.model.ISSNow;
import com.example.tesseract.cryptomonitoring.network.services.ISSNowResponse;

import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
    private static final String URL = "https://api.wheretheiss.at";


    private static MutableLiveData<ISSNow> data = new MutableLiveData<>();

    private static Retrofit retrofit = null;

    private static Retrofit getRetrofitClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .callbackExecutor(Executors.newSingleThreadExecutor())
                    .build();
        }
        return retrofit;
    }

    public static LiveData<ISSNow> getIntData() {
        return data;
    }

    public static void getISSInfo() {
        Call<ISSNow> call = getRetrofitClient().create(ISSNowResponse.class).getInfo();
        call.enqueue(new Callback<ISSNow>() {
            @Override
            public void onResponse(Call<ISSNow> call, Response<ISSNow> response) {
                if(response.body()!=null){
                    data.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ISSNow> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}