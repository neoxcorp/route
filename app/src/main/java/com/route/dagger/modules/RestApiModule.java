package com.route.dagger.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.route.model.api.MapsService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

@Module
public class RestApiModule {

    private static final String ENDPOINT = "https://maps.googleapis.com";

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder()
                .setLenient()
                .enableComplexMapKeySerialization()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
    }

    @Provides
    @Singleton
    MapsService provideService(Gson gson) {
        return new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .setConverter(new GsonConverter(gson))
                .build()
                .create(MapsService.class);
    }

}
