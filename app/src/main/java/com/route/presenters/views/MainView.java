package com.route.presenters.views;

import com.google.android.gms.maps.model.LatLng;
import com.route.model.types.PartPath;

import java.util.List;

public interface MainView extends AbstractStatefulView {

    void foundMyLocation(LatLng location);

    void showStartAddress(String startAddress);

    void showEndAddress(String endAddress);

    void showPath(List<PartPath> partPathList);

}
