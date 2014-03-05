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

import com.deveo.android.core.Event;
import com.deveo.android.core.Project;
import com.deveo.android.core.Session;
import com.deveo.android.core.User;

import java.util.Map;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.QueryMap;

public interface DeveoService {

    @FormUrlEncoded
    @POST("/account/sessions")
    void authenticate(@Field("company") String company, @Field("email") String email, @Field("id") String id, @Field("password") String password, Callback<Session> callback);

    @GET("/api/v0/users/{id}")
    void getUser(@Header("Authorization") String authorization, @Path("id") String id, Callback<User> callback);

    @GET("/api/v0/users/{id}/projects")
    void getUserProjects(@Header("Authorization") String authorization, @Path("id") String id, Callback<MetadataResults<Project>> callback);

    @GET("/api/v0/events")
    void getEvents(@Header("Authorization") String authorization, @QueryMap Map<String, String> options, Callback<MetadataResults<Event>> callback);

}
