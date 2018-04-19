package com.example.tesseract.cryptomonitoring.ui.activity;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.tesseract.cryptomonitoring.R;
import com.example.tesseract.cryptomonitoring.network.RetrofitFactory;
import com.example.tesseract.cryptomonitoring.network.model.ISSNow;
import com.example.tesseract.cryptomonitoring.presentation.presenter.MonitorPresenter;
import com.example.tesseract.cryptomonitoring.presentation.view.MonitorView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MonitorActivity extends MvpAppCompatActivity implements MonitorView,OnMapReadyCallback {
    public static final String TAG = "MonitorActivity";
    @InjectPresenter
    MonitorPresenter mMonitorPresenter;

    private GoogleMap mMap;
    private Marker lastMarker;

    @BindView(R.id.activity_monitor_view_main_layout)
    ConstraintLayout mainLayout;


    public static Intent getIntent(final Context context) {
        return new Intent(context, MonitorActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor);
        ButterKnife.bind(this);
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.activity_monitor_map_view);
        mapFragment.getMapAsync(this);
        mMonitorPresenter.init();
        RetrofitFactory.getIntData().observe(this, new Observer<ISSNow>() {
            @Override
            public void onChanged(@Nullable ISSNow issNow) {
                if (issNow != null) {
                    updateCoordinates(issNow.longitude, issNow.latitude);
                }
            }
        });
    }

    @Override
    public void setErrorMessage(String message) {
        Snackbar.make(mainLayout, message, Snackbar.LENGTH_SHORT).show();
    }

    private void updateCoordinates(Double longitude, Double latitude){
        lastMarker.setPosition(new LatLng(latitude,longitude));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng pos = new LatLng(0, 0);
        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.iss);
        MarkerOptions markerOP = new MarkerOptions().position(pos).title("ISS").icon(icon);
        lastMarker = mMap.addMarker(markerOP);
    }
}
