package com.example.tesseract.cryptomonitoring.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Market {
    @SerializedName("market")
    @Expose
    public String market;
    @SerializedName("price")
    @Expose
    public String price;
    @SerializedName("volume")
    @Expose
    public Double volume;
}
