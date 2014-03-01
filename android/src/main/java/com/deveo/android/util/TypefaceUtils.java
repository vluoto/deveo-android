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


import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

public class TypefaceUtils {

    public static final String ICON_ACCEPT = "\uf021";

    public static final String ICON_ADD = "\uf022";

    public static final String ICON_APP = "\uf023";

    public static final String ICON_ATTENTION = "\uf024";

    public static final String ICON_BOT = "\uf025";

    public static final String ICON_BRANCH = "\uf026";

    public static final String ICON_BUILD_ALT = "\uf027";

    public static final String ICON_BUILD_FAIL_ALT = "\uf028";

    public static final String ICON_BUILD_FAIL = "\uf029";

    public static final String ICON_BUILD_SUCCESS_ALT = "\uf02a";

    public static final String ICON_BUILD_SUCCESS = "\uf02b";

    public static final String ICON_BUILD = "\uf02c";

    public static final String ICON_CANCEL = "\uf02d";

    public static final String ICON_CLOSED = "\uf02e";

    public static final String ICON_COMMENT = "\uf02f";

    public static final String ICON_DASHBOARD = "\uf030";

    public static final String ICON_DEVEO = "\uf031";

    public static final String ICON_DIRECTORY = "\uf032";

    public static final String ICON_EDIT = "\uf033";

    public static final String ICON_EFICODE = "\uf034";

    public static final String ICON_EVT_ADD_APP = "\uf035";

    public static final String ICON_EVT_ADD_BOT = "\uf036";

    public static final String ICON_EVT_ADD_BRANCH = "\uf037";

    public static final String ICON_EVT_ADD_GROUP = "\uf038";

    public static final String ICON_EVT_ADD_PAGE = "\uf039";

    public static final String ICON_EVT_ADD_PROJECT = "\uf03a";

    public static final String ICON_EVT_ADD_PUSH = "\uf03b";

    public static final String ICON_EVT_ADD_REPOSITORY = "\uf03c";

    public static final String ICON_EVT_ADD_TAG_ALT = "\uf03d";

    public static final String ICON_EVT_ADD_TAG = "\uf03e";

    public static final String ICON_EVT_ADD_USER = "\uf03f";

    public static final String ICON_EVT_REMOVE_APP = "\uf040";

    public static final String ICON_EVT_REMOVE_BOT = "\uf041";

    public static final String ICON_EVT_REMOVE_BRANCH = "\uf042";

    public static final String ICON_EVT_REMOVE_GROUP = "\uf043";

    public static final String ICON_EVT_REMOVE_PAGE = "\uf044";

    public static final String ICON_EVT_REMOVE_PROJECT = "\uf045";

    public static final String ICON_EVT_REMOVE_REPOSITORY = "\uf046";

    public static final String ICON_EVT_REMOVE_TAG_ALT = "\uf047";

    public static final String ICON_EVT_REMOVE_TAG = "\uf048";

    public static final String ICON_EVT_REMOVE_USER = "\uf049";

    public static final String ICON_FEED = "\uf04a";

    public static final String ICON_FULLSCREEN = "\uf04b";

    public static final String ICON_GIT = "\uf04c";

    public static final String ICON_GROUP_ALT = "\uf04d";

    public static final String ICON_GROUP = "\uf04e";

    public static final String ICON_HELP = "\uf04f";

    public static final String ICON_HOOK = "\uf050";

    public static final String ICON_IMAGE = "\uf051";

    public static final String ICON_INFO_ALT = "\uf052";

    public static final String ICON_INFO = "\uf053";

    public static final String ICON_INTERACTION_DOWN = "\uf054";

    public static final String ICON_INTERACTION_LEFT = "\uf055";

    public static final String ICON_INTERACTION_RIGHT = "\uf056";

    public static final String ICON_INTERACTION_UP = "\uf057";

    public static final String ICON_KEY = "\uf058";

    public static final String ICON_MERCURIAL = "\uf059";

    public static final String ICON_MESSAGE = "\uf05a";

    public static final String ICON_NOTIFICATION = "\uf05b";

    public static final String ICON_OPEN = "\uf05c";

    public static final String ICON_PAGE = "\uf05d";

    public static final String ICON_PIRATE = "\uf05e";

    public static final String ICON_PROJECT = "\uf05f";

    public static final String ICON_PULL = "\uf060";

    public static final String ICON_PUSH = "\uf061";

    public static final String ICON_QUIT = "\uf062";

    public static final String ICON_REFRESH = "\uf063";

    public static final String ICON_REMOVE = "\uf064";

    public static final String ICON_REPOSITORY = "\uf065";

    public static final String ICON_SEARCH = "\uf066";

    public static final String ICON_SETTINGS_ALT = "\uf067";

    public static final String ICON_SETTINGS = "\uf068";

    public static final String ICON_SNAPSHOT = "\uf069";

    public static final String ICON_STORY = "\uf06a";

    public static final String ICON_SUBTRACT = "\uf06b";

    public static final String ICON_SUBVERSION = "\uf06c";

    public static final String ICON_TAG_ALT = "\uf06d";

    public static final String ICON_TAG = "\uf06e";

    public static final String ICON_TASK = "\uf06f";

    public static final String ICON_TEXT = "\uf070";

    public static final String ICON_TIP = "\uf071";

    public static final String ICON_TWITTER = "\uf072";

    public static final String ICON_USER = "\uf073";

    public static final String ICON_WEBDAV = "\uf074";

    public static final String ICON_WIKI_ALT_PEN = "\uf075";

    public static final String ICON_WIKI_ALT = "\uf076";

    public static final String ICON_WIKI_ONE = "\uf077";

    public static final String ICON_WIKI = "\uf078";

    private static final String ICONS_PATH = "fonts/deveo_icons.ttf";

    private static Typeface ICONS;

    public static Typeface getIcons(final Context context) {
        if (ICONS == null)
            ICONS = getTypeface(context, ICONS_PATH);
        return ICONS;
    }

    public static void setIcons(final TextView... textViews) {
        Typeface typeface = getIcons(textViews[0].getContext());
        for (TextView textView : textViews) {
            textView.setTypeface(typeface);
        }
    }

    public static Typeface getTypeface(final Context context, final String path) {
        return Typeface.createFromAsset(context.getAssets(), path);
    }

}
