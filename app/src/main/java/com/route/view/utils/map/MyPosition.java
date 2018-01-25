package com.route.view.utils.map;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

class MyPosition {

    private final GoogleMap map;

    private final BitmapDescriptor myIcon;

    private Marker myPositionMarker;

    MyPosition(GoogleMap map) {
        this.map = map;
        this.myIcon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE);
    }

    void showMyPosition(LatLng latLng) {
        removeMyMarkerIfNeed();
        myPositionMarker = map.addMarker(new MarkerOptions().position(latLng).title("I'm here!")
                .icon(myIcon));

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng)
                .tilt(20)
                .zoom(17)
                .build();

        map.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));
    }

    private void removeMyMarkerIfNeed() {
        if (myPositionMarker != null) {
            myPositionMarker.remove();
        }
    }
}
