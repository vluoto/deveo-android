package com.deveo.android.core;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class Session {

    @SerializedName("api_status")
    private int apiStatus;

    private Map<String, String> account;

    private Map<String, String> company;

    public int getApiStatus() {
        return apiStatus;
    }

    public void setApiStatus(int apiStatus) {
        this.apiStatus = apiStatus;
    }

    public Map<String, String> getAccount() {
        return account;
    }

    public void setAccount(Map<String, String> account) {
        this.account = account;
    }

    public Map<String, String> getCompany() {
        return company;
    }

    public void setCompany(Map<String, String> company) {
        this.company = company;
    }

    public String getAccountId() {
        return getAccount().get("id");
    }

    public String getAccountKey() {
        return getAccount().get("key");
    }

    public String getCompanyId() {
        return getCompany().get("id");
    }

    public String getCompanyKey() {
        return getCompany().get("key");
    }

}
