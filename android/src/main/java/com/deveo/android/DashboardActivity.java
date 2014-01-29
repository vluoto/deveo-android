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

import android.accounts.AccountManager;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.deveo.android.accounts.AccountAuthenticator;
import com.deveo.android.api.DeveoAPIKeys;
import com.deveo.android.api.DeveoClient;
import com.deveo.android.api.MetadataResults;
import com.deveo.android.core.Project;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.novoda.imageloader.core.loader.Loader;
import com.novoda.imageloader.core.model.ImageTagFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class DashboardActivity extends ListActivity {

    static final String TAG = DashboardActivity.class.getSimpleName();

    static final String COLUMN_AVATAR = "avatar";

    static final String COLUMN_PROJECT = "project";

    static final String[] FROM = new String[]{COLUMN_AVATAR, COLUMN_PROJECT};

    static final int[] TO = new int[]{R.id.image_list_item_avatar, R.id.image_list_item_label};

    static final int REQUEST_AUTHENTICATE_ACCOUNT = 1337;

    private DeveoClient client;

    private Loader imageLoader;

    private ImageTagFactory imageTagFactory;

    private Context context;

    private SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this;
        client = DeveoApplication.getClient();
        imageLoader = DeveoApplication.getImageManager().getLoader();
        imageTagFactory = ImageTagFactory.newInstance(context, R.drawable.bg_img_loading);

        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra(LoginActivity.PARAM_AUTHTOKEN_TYPE, AccountAuthenticator.ACCOUNT_TYPE);

        startActivityForResult(intent, REQUEST_AUTHENTICATE_ACCOUNT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_AUTHENTICATE_ACCOUNT:
                if (data != null && data.getExtras() != null) {
                    Bundle extras = data.getExtras();

                    DeveoAPIKeys apiKeys = new DeveoAPIKeys();
                    apiKeys.setCompanyKey(extras.getString(LoginActivity.PARAM_COMPANY_KEY));
                    apiKeys.setAccountKey(extras.getString(LoginActivity.PARAM_ACCOUNT_KEY));
                    client.setApiKeys(apiKeys);

                    loadProjects(extras.getString(AccountManager.KEY_ACCOUNT_NAME));
                }
                break;
        }
    }

    private SimpleAdapter.ViewBinder getViewBinder() {
        return new SimpleAdapter.ViewBinder() {

            @Override
            public boolean setViewValue(View view, Object data, String textRepresentation) {
                switch (view.getId()) {
                    case R.id.image_list_item_avatar:
                        view.setTag(imageTagFactory.build(getAvatarUrl(data.toString()), context));
                        imageLoader.load((ImageView) view);
                        break;

                    case R.id.image_list_item_label:
                        ((TextView) view).setText(((Project) data).getName());
                        break;
                }
                return true;
            }

        };
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // There's no way to check whether or not the Map should have generic parameters.
        @SuppressWarnings("unchecked")
        HashMap<String, Object> projectEntry = (HashMap<String, Object>) adapter.getItem(position);

        Project project = (Project) projectEntry.get(COLUMN_PROJECT);
        String json = gson.toJson(project);

        Toast.makeText(context, json, Toast.LENGTH_LONG).show();
    }

    protected void loadProjects(String login) {
        client.getUserProjects(login, new Callback<MetadataResults<Project>>() {
            @Override
            public void success(MetadataResults<Project> metadataResults, Response response) {
                List<Map<String, Object>> data = getData(metadataResults.getResults());
                adapter = new SimpleAdapter(context, data, R.layout.image_list_item, FROM, TO);
                adapter.setViewBinder(getViewBinder());
                getListView().setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.i(TAG, "FAILURE");
                Log.i(TAG, retrofitError.getMessage());
            }
        });
    }

    private List<Map<String, Object>> getData(List<Project> projects) {
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

        for (Project project : projects) {
            Map<String, Object> entry = new HashMap<String, Object>();
            entry.put(COLUMN_AVATAR, project.getMediumAvatarUrl());
            entry.put(COLUMN_PROJECT, project);
            data.add(entry);
        }

        return data;
    }

    private String getAvatarUrl(String original) {
        StringBuilder builder = new StringBuilder();
        builder.append("%s%s?");
        builder.append(String.format("plugin_key=%s&", "f048f1f3a611631a228c7f7c57037744"));
        builder.append(String.format("company_key=%s&", "e29b5239082e73223228b1cd7254e9b8"));
        builder.append(String.format("account_key=%s", "05a261503c6afa4f257b032074737396"));
        return String.format(builder.toString(), DeveoClient.API_URL, original);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
