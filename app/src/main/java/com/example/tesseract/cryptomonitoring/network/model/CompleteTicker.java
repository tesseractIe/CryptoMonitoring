package com.example.tesseract.cryptomonitoring.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompleteTicker {

    @SerializedName("ticker")
    @Expose
    public Ticker ticker;
    @SerializedName("timestamp")
    @Expose
    public Integer timestamp;
    @SerializedName("success")
    @Expose
    public Boolean success;
    @SerializedName("error")
    @Expose
    public String error;
}
