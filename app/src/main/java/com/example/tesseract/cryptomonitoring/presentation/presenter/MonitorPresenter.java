package com.example.tesseract.cryptomonitoring.presentation.presenter;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.tesseract.cryptomonitoring.network.model.CompleteTicker;
import com.example.tesseract.cryptomonitoring.presentation.view.MonitorView;
import com.example.tesseract.cryptomonitoring.storage.temp.TempStorage;
import com.example.tesseract.cryptomonitoring.storage.temp.TempUpdate;

import java.util.List;

@InjectViewState
public class MonitorPresenter extends MvpPresenter<MonitorView> implements TempUpdate {

    private List<CompleteTicker> btcList;
    private List<CompleteTicker> ethList;

    public void init() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    while(true) {
                        sleep(1000);
                        TempStorage.updateBTCData();
                        TempStorage.updateETHData();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }


    @Override
    public void updatedBTC(List<CompleteTicker> btcList) {
        this.btcList = btcList;
        getViewState().updateCrypt(ethList,btcList);
    }

    @Override
    public void updatedETH(List<CompleteTicker> ethList) {
        this.ethList = ethList;
        getViewState().updateCrypt(ethList,btcList);
    }

    @Override
    public void errorMessage(String message) {
        getViewState().setErrorMessage(message);
    }
}
