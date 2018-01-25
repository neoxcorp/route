package com.route.model.types;

import com.google.android.gms.maps.model.LatLng;

public class PartPath {

    public final LatLng start;

    public final LatLng end;

    public PartPath(LatLng start, LatLng end) {
        this.start = start;
        this.end = end;
    }

}
