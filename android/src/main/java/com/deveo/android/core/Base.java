package com.deveo.android.core;

import com.google.gson.annotations.SerializedName;

public class Base {

    @SerializedName("api_status")
    private int apiStatus;

    @SerializedName("api_timestamp")
    private String apiTimestamp;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;

    public int getApiStatus() {
        return apiStatus;
    }

    public void setApiStatus(int apiStatus) {
        this.apiStatus = apiStatus;
    }

    public String getApiTimestamp() {
        return apiTimestamp;
    }

    public void setApiTimestamp(String apiTimestamp) {
        this.apiTimestamp = apiTimestamp;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}
