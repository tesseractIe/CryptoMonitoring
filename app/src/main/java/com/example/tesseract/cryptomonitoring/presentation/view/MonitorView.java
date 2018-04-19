package com.example.tesseract.cryptomonitoring.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.example.tesseract.cryptomonitoring.network.model.CompleteTicker;
import com.example.tesseract.cryptomonitoring.network.model.Market;

import java.util.List;

public interface MonitorView extends MvpView {
    void updateCrypt(List<CompleteTicker> ethList, List<CompleteTicker> btcList);
    void setErrorMessage(String message);
}
