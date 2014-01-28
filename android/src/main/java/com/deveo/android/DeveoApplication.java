package com.deveo.android;

import android.app.Application;

import com.novoda.imageloader.core.ImageManager;
import com.novoda.imageloader.core.LoaderSettings;

public class DeveoApplication extends Application {

    private static ImageManager imageManager;

    @Override
    public void onCreate() {
        super.onCreate();

        LoaderSettings settings = new LoaderSettings.SettingsBuilder()
                .withDisconnectOnEveryCall(true).build(this);

        imageManager = new ImageManager(this, settings);
    }

    public static final ImageManager getImageManager() {
        return imageManager;
    }

}
