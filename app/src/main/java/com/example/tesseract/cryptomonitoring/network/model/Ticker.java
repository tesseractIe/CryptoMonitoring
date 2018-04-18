package com.example.tesseract.cryptomonitoring.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Ticker {
    @SerializedName("base")
    @Expose
    public String base;
    @SerializedName("target")
    @Expose
    public String target;
    @SerializedName("price")
    @Expose
    public String price;
    @SerializedName("volume")
    @Expose
    public String volume;
    @SerializedName("change")
    @Expose
    public String change;
    @SerializedName("markets")
    @Expose
    public List<Market> markets = null;
}
