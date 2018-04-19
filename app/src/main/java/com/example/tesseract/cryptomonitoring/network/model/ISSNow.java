package com.example.tesseract.cryptomonitoring.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ISSNow {
    @SerializedName("name")
    public String name;

    @SerializedName("id")
    public Long id;

    @SerializedName("latitude")
    public Double latitude;

    @SerializedName("longitude")
    public Double longitude;

    @SerializedName("altitude")
    public Double altitude;

    @SerializedName("velocity")
    public Double velocity;

    @SerializedName("visibility")
    public String visibility;

    @SerializedName("footprint")
    public Double footprint;

    @SerializedName("timestamp")
    public Long timestamp;

    @SerializedName("daynum")
    public Double daynum;

    @SerializedName("solar_lat")
    public Double solarLat;

    @SerializedName("solar_lon")
    public Double solarLon;

    @SerializedName("units")
    public String units;

}
