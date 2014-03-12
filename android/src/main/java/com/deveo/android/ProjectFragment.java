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

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.deveo.android.api.DeveoClient;
import com.deveo.android.api.MetadataResults;
import com.deveo.android.core.Event;
import com.deveo.android.util.EventUtils;
import com.deveo.android.util.TypefaceUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public abstract class ProjectFragment extends Fragment {

    private static final String TAG = ProjectSCMFragment.class.getSimpleName();

    private DeveoClient client;

    protected ProjectEventAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        client = DeveoApplication.getClient();
        adapter = new ProjectEventAdapter(getActivity(), R.layout.event_list_item, new ArrayList<Event>());
    }

    protected void loadEvents(Map<String, String> options) {
        client.getEvents(options, new Callback<MetadataResults<Event>>() {
            @Override
            public void success(MetadataResults<Event> metadataResults, Response response) {
                for (Event event : metadataResults.getResults()) {
                    adapter.add(event);
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

    private class ProjectEventAdapter extends ArrayAdapter<Event> {

        private int resource;

        public ProjectEventAdapter(Context context, int resource, List<Event> events) {
            super(context, resource, events);

            this.resource = resource;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getActivity());
                convertView = inflater.inflate(resource, parent, false);
            }

            if (convertView != null) {
                Event event = getItem(position);

                TextView tvIcon = (TextView) convertView.findViewById(R.id.event_list_item_icon);
                tvIcon.setText(EventUtils.getIcon(event));
                tvIcon.setTextColor(EventUtils.getIconColor(event));

                TextView tvText = (TextView) convertView.findViewById(R.id.event_list_item_text);
                tvText.setText(EventUtils.formatEvent(event));

                TypefaceUtils.setIcons(tvIcon);
            }

            return convertView;
        }

    }

}
