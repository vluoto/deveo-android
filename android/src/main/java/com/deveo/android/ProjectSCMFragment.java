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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.deveo.android.core.Event;
import com.deveo.android.util.APIUtils;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ProjectSCMFragment extends ProjectFragment {

    private static final String[] TARGETS = {Event.TARGET_REPOSITORY, Event.TARGET_PUSH, Event.TARGET_BRANCH, Event.TARGET_TAG};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_project_scm, container, false);

        if (rootView != null) {
            ListView listView = (ListView) rootView.findViewById(R.id.list_view_events_project_scm);
            listView.setAdapter(adapter);
        }

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        Map<String, String> options = new HashMap<String, String>();
        options.put(Event.ATTR_PROJECT, getActivity().getIntent().getStringExtra(ProjectActivity.PARAM_PROJECT_ID));
        options.put(Event.ATTR_TARGET, StringUtils.join(TARGETS, ","));
        options.put(APIUtils.ATTR_EXPAND, Event.ATTR_SUBJECT);

        loadEvents(options);
    }

}
