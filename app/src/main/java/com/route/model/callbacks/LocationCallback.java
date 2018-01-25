package com.route.model.callbacks;

import com.google.android.gms.maps.model.LatLng;

public interface LocationCallback {

    void onLocationChanged(LatLng newLocation);

}
