package com.deveo.android.api;

import com.deveo.android.api.MetadataResults;
import com.google.gson.InstanceCreator;

import java.lang.reflect.Type;

public class MetadataResultsInstanceCreator implements InstanceCreator<MetadataResults<?>> {

    @Override
    public MetadataResults<?> createInstance(Type type) {
        return new MetadataResults();
    }

}
