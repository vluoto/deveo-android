package com.deveo.android.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class ApiManager {

    private static final String API_URL = "https://deveo.com";

    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(MetadataResults.class, new MetadataResultsInstanceCreator())
            .create();

    private static final RestAdapter REST_ADAPTER = new RestAdapter.Builder()
            .setServer(API_URL)
            .setConverter(new GsonConverter(GSON))
            .build();

    private static final DeveoService DEVEO_SERVICE = REST_ADAPTER.create(DeveoService.class);

    public static DeveoService getService() {
        return DEVEO_SERVICE;
    }

}
