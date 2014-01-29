/*
 * Copyright 2014 Deveo Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the “License”);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an “AS IS” BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
