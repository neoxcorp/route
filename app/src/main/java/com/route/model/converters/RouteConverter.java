package com.route.model.converters;

import com.google.android.gms.maps.model.LatLng;
import com.route.model.api.types.rout.pojo.Leg;
import com.route.model.api.types.rout.pojo.Route;
import com.route.model.api.types.rout.pojo.RouteResponse;
import com.route.model.api.types.rout.pojo.Step;
import com.route.model.types.PartPath;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class RouteConverter {

    @Inject
    public RouteConverter() {}

    public List<PartPath> getPartPathsFromRouteResponse(RouteResponse routeResponse) {
        List<PartPath> partPaths = new ArrayList<>();
        List<Route> routes = routeResponse.getRoutes();
        if (routes != null && routes.size() > 0) {
            List<Leg> legs = routes.get(0).getLegs();
            if (legs != null && legs.size() > 0) {
                List<Step> steps = legs.get(0).getSteps();
                if (steps != null && steps.size() > 0) {
                    for (Step step : steps) {
                        partPaths.add(getPartPathFromStep(step));
                    }
                }
            }
        }
        return partPaths;
    }

    private PartPath getPartPathFromStep(Step step) {
        LatLng start = new LatLng(step.getStartLocation().getLat(), step.getStartLocation().getLng());
        LatLng end = new LatLng(step.getEndLocation().getLat(), step.getEndLocation().getLng());
        return new PartPath(start, end);
    }

}
