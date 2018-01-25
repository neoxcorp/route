package com.route.model.api;

import com.route.model.api.types.rout.pojo.RouteResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface MapsService {

    @GET("/maps/api/directions/json")
    void getRoute(@Query("origin") String origin,
                  @Query("destination") String destination,
                  Callback<RouteResponse> callback);

}
