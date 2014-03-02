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

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.deveo.android.api.DeveoClient;
import com.deveo.android.api.MetadataResults;
import com.deveo.android.core.Event;
import com.deveo.android.util.EventUtils;
import com.deveo.android.util.TypefaceUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ProjectFragment extends Fragment {

    private static final String TAG = ProjectSCMFragment.class.getSimpleName();

    private static final String COLUMN_ICON = "icon";

    private static final String COLUMN_TEXT = "text";

    private static final String[] FROM = {COLUMN_ICON, COLUMN_TEXT};

    private static final int[] TO = {R.id.event_list_item_icon, R.id.event_list_item_text};

    private DeveoClient client;

    protected SimpleAdapter adapter;

    private List<Map<String, String>> events;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        client = DeveoApplication.getClient();
        events = new ArrayList<>();
        adapter = new SimpleAdapter(getActivity(), events, R.layout.event_list_item, FROM, TO);
        adapter.setViewBinder(getViewBinder());
    }

    private SimpleAdapter.ViewBinder getViewBinder() {
        return new SimpleAdapter.ViewBinder() {

            @Override
            public boolean setViewValue(View view, Object data, String textRepresentation) {
                switch (view.getId()) {
                    case R.id.event_list_item_icon:
                        TextView textView = (TextView) view;
                        textView.setText(data.toString());
                        TypefaceUtils.setIcons(textView);
                        break;

                    case R.id.event_list_item_text:
                        ((TextView) view).setText(data.toString());
                        break;
                }

                return true;
            }

        };
    }

    protected void loadEvents(Map<String, String> options) {
        client.getEvents(options, new Callback<MetadataResults<Event>>() {
            @Override
            public void success(MetadataResults<Event> metadataResults, Response response) {
                for (Event event : metadataResults.getResults()) {
                    Map<String, String> entry = new HashMap<>();
                    entry.put(COLUMN_ICON, EventUtils.getIcon(event));
                    entry.put(COLUMN_TEXT, EventUtils.formatEvent(event));
                    events.add(entry);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.i(TAG, "FAILURE");
                Log.i(TAG, error.getMessage() + "");
            }
        });
    }

}
