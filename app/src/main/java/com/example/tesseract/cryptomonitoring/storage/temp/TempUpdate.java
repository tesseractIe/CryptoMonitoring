package com.example.tesseract.cryptomonitoring.storage.temp;

import com.example.tesseract.cryptomonitoring.network.model.CompleteTicker;

import java.util.List;

public interface TempUpdate {
    void updatedBTC(List<CompleteTicker> btcList);
    void updatedETH(List<CompleteTicker> ethList);
    void errorMessage(String message);
}
