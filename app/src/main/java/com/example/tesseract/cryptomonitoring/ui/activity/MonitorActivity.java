package com.example.tesseract.cryptomonitoring.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.Spinner;

import com.example.tesseract.cryptomonitoring.network.model.CompleteTicker;
import com.example.tesseract.cryptomonitoring.network.model.Market;
import com.example.tesseract.cryptomonitoring.presentation.view.MonitorView;
import com.example.tesseract.cryptomonitoring.presentation.presenter.MonitorPresenter;

import com.arellomobile.mvp.MvpAppCompatActivity;

import com.example.tesseract.cryptomonitoring.R;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.tesseract.cryptomonitoring.storage.temp.TempStorage;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnItemSelected;

public class MonitorActivity extends MvpAppCompatActivity implements MonitorView {
    public static final String TAG = "MonitorActivity";
    @InjectPresenter
    MonitorPresenter mMonitorPresenter;

    @BindView(R.id.activity_monitor_view_crypto_graph)
    GraphView graphView;

    @BindView(R.id.activity_monitor_spinner_platform)
    Spinner platformSpinner;

    @BindView(R.id.activity_monitor_view_main_layout)
    ConstraintLayout mainLayout;

    private boolean eth = false;

    @OnCheckedChanged(R.id.activity_monitor_check_box)
    void cryptSelected(CheckBox button, boolean checked) {
        eth = checked;
        if (checked) {
            button.setText("ETH");
        } else {
            button.setText("BTC");
        }
    }

    @OnItemSelected(R.id.activity_monitor_spinner_platform)
    public void spinnerItemSelected(Spinner spinner, int position) {

    }

    public static Intent getIntent(final Context context) {
        return new Intent(context, MonitorActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor);
        ButterKnife.bind(this);
        mMonitorPresenter.init();
    }

    private void setGraph(List<CompleteTicker> tickets) {
        LineGraphSeries<DataPoint> graphs = new LineGraphSeries<>();
        for (CompleteTicker ticket : tickets) {
            Log.e(TAG,ticket.timestamp.toString());
        }
        graphView.addSeries(graphs);
    }

    @Override
    public void updateCrypt(List<CompleteTicker> ethList, List<CompleteTicker> btcList) {
        if(eth){
            setGraph(ethList);
        }else{
            setGraph(btcList);
        }
    }

    @Override
    public void setErrorMessage(String message) {
        Snackbar.make(mainLayout, message, Snackbar.LENGTH_SHORT).show();
    }

}
