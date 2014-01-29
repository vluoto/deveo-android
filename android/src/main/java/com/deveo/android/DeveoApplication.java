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

package com.deveo.android;

import android.app.Application;

import com.deveo.android.api.DeveoClient;
import com.novoda.imageloader.core.ImageManager;
import com.novoda.imageloader.core.LoaderSettings;

public class DeveoApplication extends Application {

    private static DeveoClient client;

    private static ImageManager imageManager;

    @Override
    public void onCreate() {
        super.onCreate();

        client = new DeveoClient();

        LoaderSettings settings = new LoaderSettings.SettingsBuilder()
                .withDisconnectOnEveryCall(true).build(this);

        imageManager = new ImageManager(this, settings);
    }

    public static final DeveoClient getClient() {
        return client;
    }

    public static final ImageManager getImageManager() {
        return imageManager;
    }

}
