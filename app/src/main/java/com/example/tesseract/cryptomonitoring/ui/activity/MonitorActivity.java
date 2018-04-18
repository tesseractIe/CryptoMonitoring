package com.example.tesseract.cryptomonitoring.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;

import com.example.tesseract.cryptomonitoring.network.model.CompleteTicker;
import com.example.tesseract.cryptomonitoring.network.model.Market;
import com.example.tesseract.cryptomonitoring.presentation.view.MonitorView;
import com.example.tesseract.cryptomonitoring.presentation.presenter.MonitorPresenter;

import com.arellomobile.mvp.MvpAppCompatActivity;

import com.example.tesseract.cryptomonitoring.R;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class MonitorActivity extends MvpAppCompatActivity implements MonitorView {
    public static final String TAG = "MonitorActivity";
    @InjectPresenter
    MonitorPresenter mMonitorPresenter;

    @BindView(R.id.activity_monitor_view_crypto_graph)
    GraphView graphView;

    @OnCheckedChanged(R.id.activity_monitor_check_box)
    void cryptSelected(CheckBox button, boolean checked) {
        if(checked){
            button.setText("ETH");
        }else{
            button.setText("BTC");
        }
    }

    @OnItemSelected(R.id.activity_monitor_spinner_platform)
    public void spinnerItemSelected(Spinner spinner, int position) {

    }

    @BindView(R.id.activity_monitor_view_main_layout)
    ConstraintLayout mainLayout;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, MonitorActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor);
        ButterKnife.bind(this);
        mMonitorPresenter.init();
    }

    @Override
    public void updateGraph(List<CompleteTicker> tickets) {
        LineGraphSeries<DataPoint> graphs = new LineGraphSeries<>();
        for(CompleteTicker ticket : tickets){
            //TODO: parse tickets
        }
        graphView.addSeries(graphs);
    }

    @Override
    public void updatePlatforms(List<Market> markets) {

    }

    @Override
    public void setErrorMessage(String message) {
        Snackbar.make(mainLayout,message,Snackbar.LENGTH_SHORT).show();
    }
}
