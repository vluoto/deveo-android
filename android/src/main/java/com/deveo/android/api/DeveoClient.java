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

import com.deveo.android.core.Project;
import com.deveo.android.core.Session;
import com.deveo.android.core.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class DeveoClient {

    public static final String API_URL = "https://deveo.com";

    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(MetadataResults.class, new MetadataResultsInstanceCreator())
            .create();

    private static final RestAdapter REST_ADAPTER = new RestAdapter.Builder()
            .setServer(API_URL)
            .setConverter(new GsonConverter(GSON))
            .build();

    private static final DeveoService DEVEO_SERVICE = REST_ADAPTER.create(DeveoService.class);

    private DeveoAPIKeys apiKeys;

    public void setApiKeys(DeveoAPIKeys apiKeys) {
        this.apiKeys = apiKeys;
    }

    public DeveoAPIKeys getApiKeys() {
        return apiKeys;
    }

    /**
     * Creates a new session.
     *
     * @param company  The company short name.
     * @param login    The user email or unique identifier.
     * @param password The user password.
     * @param callback The Retrofit ResponseCallback.
     */
    public void authenticate(String company, String login, String password, Callback<Session> callback) {
        DEVEO_SERVICE.authenticate(company, login, login, password, callback);
    }

    /**
     * Gets a single user.
     *
     * @param userId   The unique identifier of the user.
     * @param callback The Retrofit ResponseCallback.
     */
    public void getUser(String userId, Callback<User> callback) {
        DEVEO_SERVICE.getUser(apiKeys.toAuthorizationHeader(), userId, callback);
    }

    /**
     * Gets projects for a user.
     *
     * @param userId   The unique identifier of the user.
     * @param callback The Retrofit ResponseCallback.
     */
    public void getUserProjects(String userId, Callback<MetadataResults<Project>> callback) {
        DEVEO_SERVICE.getUserProjects(apiKeys.toAuthorizationHeader(), userId, callback);
    }

}
