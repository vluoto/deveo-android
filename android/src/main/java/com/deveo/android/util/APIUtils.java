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

package com.deveo.android.util;

import com.deveo.android.api.DeveoAPIKeys;
import com.deveo.android.api.DeveoClient;

public class APIUtils {

    public static final String ATTR_EXPAND = "expand";

    public static String getAuthorizedUrl(DeveoAPIKeys apiKeys, String relativeUrl) {
        return String.format("%s%s%s", DeveoClient.API_URL, relativeUrl, apiKeys.toQueryParams());
    }

}
