package com.route.model.repositories;

import com.google.android.gms.maps.model.LatLng;
import com.route.model.api.MapsService;
import com.route.model.api.types.rout.pojo.RouteResponse;
import com.route.model.callbacks.RouteCallback;
import com.route.model.converters.RouteConverter;

import javax.inject.Inject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class RoutRepository {

    private RouteCallback routeCallback;

    private final MapsService service;
    private final RouteConverter routeConverter;

    @Inject
    public RoutRepository(MapsService service, RouteConverter routeConverter) {
        this.service = service;
        this.routeConverter = routeConverter;
    }

    public void setRouteCallback(RouteCallback routeCallback) {
        this.routeCallback = routeCallback;
    }

    public void getRout(LatLng start, LatLng end) {
        String origin = start.latitude + "," + start.longitude;
        String destination = end.latitude + "," + end.longitude;
        service.getRoute(origin, destination, new Callback<RouteResponse>() {
            @Override
            public void success(RouteResponse routeResponse, Response response) {
                if (routeCallback != null) {
                    routeCallback.onRoute(routeConverter.getPartPathsFromRouteResponse(routeResponse));
                }
            }

            @Override
            public void failure(RetrofitError error) {
                if (routeCallback != null) {
                    routeCallback.onError(error.getKind());
                }
            }
        });
    }

}
