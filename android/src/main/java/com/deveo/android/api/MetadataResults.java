package com.deveo.android.api;

import java.util.List;

public class MetadataResults<T> {

    private List<T> results;

    public MetadataResults() {
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

}
