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

package com.deveo.android.api;

public class DeveoAPIKeys {

    // API Access Plugin
    static final String PLUGIN_KEY = "3c94d47d6257ca0d3bc54a9b6a91aa64";

    private String companyKey;

    private String accountKey;

    public DeveoAPIKeys() {
    }

    public DeveoAPIKeys(String companyKey, String accountKey) {
        this.companyKey = companyKey;
        this.accountKey = accountKey;
    }

    public String getCompanyKey() {
        return companyKey;
    }

    public void setCompanyKey(String companyKey) {
        this.companyKey = companyKey;
    }

    public String getAccountKey() {
        return accountKey;
    }

    public void setAccountKey(String accountKey) {
        this.accountKey = accountKey;
    }

    public String toAuthorizationHeader() {
        StringBuilder builder = new StringBuilder("deveo ");
        builder.append(String.format("plugin_key='%s',", PLUGIN_KEY));
        builder.append(String.format("company_key='%s',", companyKey));
        builder.append(String.format("account_key='%s'", accountKey));
        return builder.toString();
    }

}
