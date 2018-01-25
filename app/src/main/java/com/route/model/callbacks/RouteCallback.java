package com.route.model.callbacks;

import com.route.model.types.PartPath;

import java.util.List;

import retrofit.RetrofitError;

public interface RouteCallback {

    void onRoute(List<PartPath> partPathList);

    void onError(RetrofitError.Kind kind);

}
