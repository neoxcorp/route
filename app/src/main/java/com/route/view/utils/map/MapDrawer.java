package com.route.view.utils.map;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.route.model.types.PartPath;

import java.util.List;

public class MapDrawer {

    private final GoogleMap map;
    private final Context context;

    private final MyPosition myPosition;
    private final FirstLastPosition firstLastPosition;
    private final PathDrawer pathDrawer;

    public MapDrawer(Context context, GoogleMap map) {
        this.context = context;
        this.map = map;
        this.myPosition = new MyPosition(map);
        this.firstLastPosition = new FirstLastPosition(map);
        this.pathDrawer = new PathDrawer(context, map);
    }

    public void showMe(LatLng latLng) {
        myPosition.showMyPosition(latLng);
    }

    public void showStart(LatLng latLng) {
        firstLastPosition.showFirstPosition(latLng);
    }

    public void showEnd(LatLng latLng) {
        firstLastPosition.showLastPosition(latLng);
    }

    public void moveToPoint(LatLng latLng) {
        firstLastPosition.moveTo(latLng);
    }

    public void showPath(List<PartPath> partPathList) {
        pathDrawer.drawPath(partPathList);
    }

    public void pathPositioning() {
        pathDrawer.showAllPointsOfPath();
    }

}
