package com.route.view.utils.map;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

class FirstLastPosition {

    private final GoogleMap map;

    private final BitmapDescriptor firstIcon;
    private final BitmapDescriptor lastIcon;

    private Marker firstPositionMarker;
    private Marker lastPositionMarker;

    FirstLastPosition(GoogleMap map) {
        this.map = map;
        this.firstIcon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED);
        this.lastIcon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN);
    }

    void showFirstPosition(LatLng latLng) {
        removeFirstMarkerIfNeed();
        firstPositionMarker = map.addMarker(new MarkerOptions().position(latLng).title("Start of path")
                .icon(firstIcon));
    }

    private void removeFirstMarkerIfNeed() {
        if (firstPositionMarker != null) {
            firstPositionMarker.remove();
        }
    }

    void showLastPosition(LatLng latLng) {
        removeLastMarkerIfNeed();
        lastPositionMarker = map.addMarker(new MarkerOptions().position(latLng).title("End of path")
                .icon(lastIcon));
    }

    void moveTo(LatLng latLng) {
        map.animateCamera(CameraUpdateFactory.newLatLng(latLng));
    }

    private void removeLastMarkerIfNeed() {
        if (lastPositionMarker != null) {
            lastPositionMarker.remove();
        }
    }

}
