package com.example.tesseract.cryptomonitoring.presentation.presenter;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.tesseract.cryptomonitoring.network.RetrofitFactory;
import com.example.tesseract.cryptomonitoring.presentation.view.MonitorView;

import static java.lang.Thread.sleep;

@InjectViewState
public class MonitorPresenter extends MvpPresenter<MonitorView> {

    public void init() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        RetrofitFactory.getISSInfo();
                        sleep(2000);
                    } catch (InterruptedException e) {
                        getViewState().setErrorMessage(e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
