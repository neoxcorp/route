package com.route.presenters;

import android.app.Activity;

import com.google.android.gms.maps.model.LatLng;
import com.route.model.callbacks.LocationCallback;
import com.route.model.callbacks.RouteCallback;
import com.route.model.repositories.AddressRepository;
import com.route.model.repositories.DbRepository;
import com.route.model.repositories.LocationRepository;
import com.route.model.repositories.RoutRepository;
import com.route.model.types.PartPath;
import com.route.presenters.base.BasePresenter;
import com.route.presenters.views.MainView;
import com.route.utils.LogTag;

import java.util.List;

import javax.inject.Inject;

import retrofit.RetrofitError;

public class MainPresenter extends BasePresenter<MainView> implements RouteCallback, LocationCallback {

    private final RoutRepository routRepository;
    private final AddressRepository addressRepository;
    private final LocationRepository locationRepository;
    private final DbRepository dbRepository;

    private Activity activity;

    private LatLng start;
    private LatLng end;

    private boolean isHavePath = false;

    @Inject
    public MainPresenter(RoutRepository routRepository, AddressRepository addressRepository,
                         LocationRepository locationRepository, DbRepository dbRepository) {
        this.routRepository = routRepository;
        this.addressRepository = addressRepository;
        this.locationRepository = locationRepository;
        this.dbRepository = dbRepository;
        routRepository.setRouteCallback(this);
    }

    @Override
    public void onStart(MainView view) {
        super.onStart(view);

        locationRepository.initLocationServices(activity, this);
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void getStartAddress(LatLng latLng) {
        setStartAddress(latLng);

        String startAddress = addressRepository.getCompleteAddressString(latLng.latitude, latLng.longitude);
        dbRepository.savePlace(startAddress, start);
        view.showStartAddress(startAddress);
    }

    public void getEndAddress(LatLng latLng) {
        setEndAddress(latLng);

        String endAddress = addressRepository.getCompleteAddressString(latLng.latitude, latLng.longitude);
        dbRepository.savePlace(endAddress, end);
        view.showEndAddress(endAddress);
    }

    public void setEndAddress(LatLng latLng) {
        this.end = latLng;
        if (start != null && isHavePath) {
            getRoute();
        }
    }

    public void setStartAddress(LatLng latLng) {
        this.start = latLng;
        if (end != null && isHavePath) {
            getRoute();
        }
    }

    public void getRoute() {
        if (start != null && end != null) {
            view.showProgress();
            routRepository.getRout(start, end);
        }
    }

    public void findingMyLocation() {
        locationRepository.findingMyLocation();
    }

    @Override
    public void onRoute(List<PartPath> partPathList) {
        isHavePath = true;
        view.hideProgress();
        view.showPath(partPathList);
    }

    @Override
    public void onError(RetrofitError.Kind kind) {
        view.hideProgress();
        LogTag.e("onError: " + kind);
    }

    @Override
    public void onLocationChanged(LatLng newLocation) {
        view.foundMyLocation(newLocation);
    }
}
