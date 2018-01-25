package com.route.view.utils.map;

import android.content.Context;
import android.graphics.Color;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.route.model.types.PartPath;

import java.util.List;

class PathDrawer {

    private final GoogleMap map;
    private final Context context;

    private Polyline pathPolyline;

    PathDrawer(Context context, GoogleMap map) {
        this.context = context;
        this.map = map;
    }

    void drawPath(List<PartPath> partPathList) {
        if (partPathList.size() == 0) return;
        cleanPathIfNeed();
        pathPolyline = map.addPolyline(new PolylineOptions()
                .add(getLatLngListFromPartPaths(partPathList))
                .width(10)
                .color(Color.YELLOW));
    }

    void showAllPointsOfPath() {
        if (pathPolyline != null) {

            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            for (LatLng latLng : pathPolyline.getPoints()) {
                builder.include(latLng);
            }

            LatLngBounds bounds = builder.build();
            int padding = 50; // offset from edges of the map in pixels
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, padding);

            map.resetMinMaxZoomPreference();
            map.animateCamera(cameraUpdate);
        } else {
            Toast.makeText(context, "First you need to create a path!", Toast.LENGTH_SHORT).show();
        }
    }

    private LatLng[] getLatLngListFromPartPaths(List<PartPath> partPaths) {
        LatLng[] latLngArray = new LatLng[partPaths.size() + 1];
        int lastItem = partPaths.size() - 1;
        for (int i = 0; i < partPaths.size(); i++) {
            latLngArray[i] = partPaths.get(i).start;
            if (i == lastItem) {
                latLngArray[i + 1] = partPaths.get(i).end;
            }
        }
        return latLngArray;
    }

    private void cleanPathIfNeed() {
        if (pathPolyline != null) {
            pathPolyline.remove();
            pathPolyline = null;
        }
    }

}
