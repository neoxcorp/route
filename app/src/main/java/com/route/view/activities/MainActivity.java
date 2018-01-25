package com.route.view.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.route.AndroidApplication;
import com.route.R;
import com.route.model.repositories.LocationRepository;
import com.route.model.types.PartPath;
import com.route.presenters.MainPresenter;
import com.route.presenters.views.MainView;
import com.route.view.utils.map.MapDrawer;
import com.route.view.activities.base.BasePresenterActivity;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BasePresenterActivity implements MainView, OnMapReadyCallback {

    public static final String EXTRA_ADDRESS = "EXTRA_ADDRESS";
    public static final String EXTRA_LOC_LAT = "EXTRA_LOC_LAT";
    public static final String EXTRA_LOC_LNG = "EXTRA_LOC_LNG";

    private static final int START_POINT = 7686;
    private static final int END_POINT = 6457;

    private GoogleMap mMap;
    private MapDrawer mapDrawer;

    private EditText firstAddressEt;
    private EditText lastAddressEt;
    private Button getRouteBtn;
    private ImageButton firstAddressImb;
    private ImageButton lastAddressImb;
    private FloatingActionButton myLocationFab;
    private FloatingActionButton positioningRouteFab;

    @Inject
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((AndroidApplication) getApplication()).getApplicationComponent().inject(this);
        setPresenter(presenter);
        presenter.setActivity(this);

        initViews();
        setParamsForEditTexts();
        setListenersForButtons();
        loadMap();
    }

    private void initViews() {
        firstAddressEt = findViewById(R.id.firstAddressEt);
        lastAddressEt = findViewById(R.id.lastAddressEt);

        getRouteBtn = findViewById(R.id.getRouteBtn);

        firstAddressImb = findViewById(R.id.firstAddressImb);
        lastAddressImb = findViewById(R.id.lastAddressImb);

        myLocationFab = findViewById(R.id.myLocationFab);
        positioningRouteFab = findViewById(R.id.positioningRouteFab);
    }

    private void setParamsForEditTexts() {
        firstAddressEt.setInputType(InputType.TYPE_NULL);
        lastAddressEt.setInputType(InputType.TYPE_NULL);
    }

    private void setListenersForButtons() {
        getRouteBtn.setOnClickListener(v -> presenter.getRoute());

        firstAddressImb.setOnClickListener(v -> PlacesActivity.launch(this, START_POINT));

        lastAddressImb.setOnClickListener(v -> PlacesActivity.launch(this, END_POINT));

        myLocationFab.setOnClickListener(v -> myLocation());

        positioningRouteFab.setOnClickListener(v -> moveToPath());
    }

    private void loadMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void myLocation() {
        presenter.findingMyLocation();
    }

    private void moveToPath() {
        if (mapDrawer != null) {
            mapDrawer.pathPositioning();
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mapDrawer = new MapDrawer(this, mMap);

        mMap.setOnMapClickListener(latLng -> {
            if (firstAddressEt.isFocused()) {
                mapDrawer.showStart(latLng);
                presenter.getStartAddress(latLng);
            } else {
                mapDrawer.showEnd(latLng);
                presenter.getEndAddress(latLng);
            }
        });
    }

    @Override
    public void foundMyLocation(LatLng location) {
        mapDrawer.showMe(location);
    }

    @Override
    public void showStartAddress(String startAddress) {
        firstAddressEt.setText(startAddress);
    }

    @Override
    public void showEndAddress(String endAddress) {
        lastAddressEt.setText(endAddress);
    }

    @Override
    public void showPath(List<PartPath> partPathList) {
        mapDrawer.showPath(partPathList);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LocationRepository.REQUEST_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                presenter.findingMyLocation();
            } else {
                Toast.makeText(MainActivity.this, "Permission denied to get location",
                        Toast.LENGTH_SHORT).show();
            }
            return;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case START_POINT:
                if (resultCode == RESULT_OK) setFirstPoint(data);
                break;
            case END_POINT:
                if (resultCode == RESULT_OK) setEndPoint(data);
                break;
        }
    }

    private void setFirstPoint(Intent data) {
        String address = data.getStringExtra(EXTRA_ADDRESS);
        Double lat = data.getDoubleExtra(EXTRA_LOC_LAT, 0);
        Double lng = data.getDoubleExtra(EXTRA_LOC_LNG, 0);

        LatLng position = new LatLng(lat, lng);
        presenter.setStartAddress(position);
        mapDrawer.showStart(position);
        mapDrawer.moveToPoint(position);
        firstAddressEt.setText(address);
    }

    private void setEndPoint(Intent data) {
        String address = data.getStringExtra(EXTRA_ADDRESS);
        Double lat = data.getDoubleExtra(EXTRA_LOC_LAT, 0);
        Double lng = data.getDoubleExtra(EXTRA_LOC_LNG, 0);

        LatLng position = new LatLng(lat, lng);
        presenter.setEndAddress(position);
        mapDrawer.showEnd(position);
        mapDrawer.moveToPoint(position);
        lastAddressEt.setText(address);
    }

}
