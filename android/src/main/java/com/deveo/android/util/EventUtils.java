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

import com.deveo.android.core.Event;

import static com.deveo.android.core.Event.OPERATION_CREATED;
import static com.deveo.android.core.Event.OPERATION_DELETED;
import static com.deveo.android.core.Event.OPERATION_UPDATED;
import static com.deveo.android.core.Event.TARGET_BRANCH;
import static com.deveo.android.core.Event.TARGET_PROJECT_BOT;
import static com.deveo.android.core.Event.TARGET_PROJECT_USER;
import static com.deveo.android.core.Event.TARGET_PUSH;
import static com.deveo.android.core.Event.TARGET_REPOSITORY;
import static com.deveo.android.core.Event.TARGET_TAG;
import static com.deveo.android.util.TypefaceUtils.ICON_EVT_ADD_BOT;
import static com.deveo.android.util.TypefaceUtils.ICON_EVT_ADD_BRANCH;
import static com.deveo.android.util.TypefaceUtils.ICON_EVT_ADD_REPOSITORY;
import static com.deveo.android.util.TypefaceUtils.ICON_EVT_ADD_TAG;
import static com.deveo.android.util.TypefaceUtils.ICON_EVT_ADD_USER;
import static com.deveo.android.util.TypefaceUtils.ICON_EVT_REMOVE_BOT;
import static com.deveo.android.util.TypefaceUtils.ICON_EVT_REMOVE_BRANCH;
import static com.deveo.android.util.TypefaceUtils.ICON_EVT_REMOVE_REPOSITORY;
import static com.deveo.android.util.TypefaceUtils.ICON_EVT_REMOVE_TAG;
import static com.deveo.android.util.TypefaceUtils.ICON_EVT_REMOVE_USER;
import static com.deveo.android.util.TypefaceUtils.ICON_PUSH;
import static com.deveo.android.util.TypefaceUtils.ICON_USER;

public class EventUtils {

    /**
     * Formats a repository Event to a human-readable message based on its attributes.
     *
     * @param event The repository Event.
     * @return      The human-readable message.
     */
    public static String formatRepositoryEvent(Event event) {
        return String.format("%s %s repository %s", event.getSubject(), event.getOperation(), event.getRepository());
    }

    /**
     * Formats a push Event to a human-readable message based on its attributes.
     *
     * @param event The push Event.
     * @return      The human-readable message.
     */
    public static String formatPushEvent(Event event) {
        return String.format("%s %s a new push with %s commit(s) to %s", event.getSubject(), event.getOperation(), event.getCommitCount(), event.getRepository());
    }

    /**
     * Formats a branch Event to a human-readable message based on its attributes.
     *
     * @param event The branch Event.
     * @return      The human-readable message.
     */
    public static String formatBranchEvent(Event event) {
        return String.format("%s %s branch %s at %s", event.getSubject(), event.getOperation(), event.getRef(), event.getRepository());
    }

    /**
     * Formats a tag Event to a human-readable message based on its attributes.
     *
     * @param event The tag Event.
     * @return      The human-readable message.
     */
    public static String formatTagEvent(Event event) {
        return String.format("%s %s tag %s at %s", event.getSubject(), event.getOperation(), event.getRef(), event.getRepository());
    }

    /**
     * Formats a project user Event to a human-readable message based on its attributes.
     *
     * @param event The project user Event.
     * @return      The human-readable message.
     */
    public static String formatProjectUserEvent(Event event) {
        switch (event.getOperation()) {
            case OPERATION_CREATED:
                return String.format("%s added %s as %s to the project", event.getSubject(), event.getUser(), event.getRole());
            case OPERATION_UPDATED:
                return String.format("%s gave %s %s rights to the project", event.getSubject(), event.getUser(), event.getRole());
            default:
                return "";
        }
    }

    /**
     * Formats a project bot Event to a human-readable message based on its attributes.
     *
     * @param event The project bot Event.
     * @return      The human-readable message.
     */
    public static String formatProjectBotEvent(Event event) {
        return String.format("%s %s project bot %s", event.getSubject(), event.getOperation(), event.getBot());
    }

    /**
     * Formats an Event to a human-readable message based on its attributes.
     *
     * @param event The Event.
     * @return      The human-readable message.
     */
    public static String formatEvent(Event event) {
        switch (event.getTarget()) {
            case TARGET_REPOSITORY:
                return formatRepositoryEvent(event);
            case TARGET_PUSH:
                return formatPushEvent(event);
            case TARGET_BRANCH:
                return formatBranchEvent(event);
            case TARGET_TAG:
                return formatTagEvent(event);
            case TARGET_PROJECT_USER:
                return formatProjectUserEvent(event);
            case TARGET_PROJECT_BOT:
                return formatProjectBotEvent(event);
            default:
                return "";
        }
    }

    /**
     * Gets the appropriate Deveo icon String for a repository Event based on its operation.
     *
     * @param event The repository Event.
     * @return      The icon String or empty String in case of no match.
     */
    public static String getRepositoryIcon(Event event) {
        switch (event.getOperation()) {
            case OPERATION_CREATED:
                return ICON_EVT_ADD_REPOSITORY;
            case OPERATION_DELETED:
                return ICON_EVT_REMOVE_REPOSITORY;
            default:
                return "";
        }
    }

    /**
     * Gets the appropriate Deveo icon String for a push Event based on its operation.
     *
     * @param event The push Event.
     * @return      The icon String or empty String in case of no match.
     */
    public static String getPushIcon(Event event) {
        switch (event.getOperation()) {
            case OPERATION_CREATED:
                return ICON_PUSH;
            default:
                return "";
        }
    }

    /**
     * Gets the appropriate Deveo icon String for a branch Event based on its operation.
     *
     * @param event The branch Event.
     * @return      The icon String or empty String in case of no match.
     */
    public static String getBranchIcon(Event event) {
        switch (event.getOperation()) {
            case OPERATION_CREATED:
                return ICON_EVT_ADD_BRANCH;
            case OPERATION_DELETED:
                return ICON_EVT_REMOVE_BRANCH;
            default:
                return "";
        }
    }

    /**
     * Gets the appropriate Deveo icon String for a tag Event based on its operation.
     *
     * @param event The tag Event.
     * @return      The icon String or empty String in case of no match.
     */
    public static String getTagIcon(Event event) {
        switch (event.getOperation()) {
            case OPERATION_CREATED:
                return ICON_EVT_ADD_TAG;
            case OPERATION_DELETED:
                return ICON_EVT_REMOVE_TAG;
            default:
                return "";
        }
    }

    /**
     * Gets the appropriate Deveo icon String for a project user Event based on its operation.
     *
     * @param event The project user Event.
     * @return      The icon String or empty String in case of no match.
     */
    public static String getProjectUserIcon(Event event) {
        switch (event.getOperation()) {
            case OPERATION_CREATED:
                return ICON_EVT_ADD_USER;
            case OPERATION_UPDATED:
                return ICON_USER;
            case OPERATION_DELETED:
                return ICON_EVT_REMOVE_USER;
            default:
                return "";
        }
    }

    /**
     * Gets the appropriate Deveo icon String for a project bot Event based on its operation.
     *
     * @param event The project bot Event.
     * @return      The icon String or empty String in case of no match.
     */
    public static String getProjectBotIcon(Event event) {
        switch (event.getOperation()) {
            case OPERATION_CREATED:
                return ICON_EVT_ADD_BOT;
            case OPERATION_DELETED:
                return ICON_EVT_REMOVE_BOT;
            default:
                return "";
        }
    }

    /**
     * Gets the appropriate Deveo icon String for an Event based on its target and operation.
     *
     * @param event The Event.
     * @return      The icon String or empty String in case of no match.
     */
    public static String getIcon(Event event) {
        switch (event.getTarget()) {
            case TARGET_REPOSITORY:
                return getRepositoryIcon(event);
            case TARGET_PUSH:
                return getPushIcon(event);
            case TARGET_BRANCH:
                return getBranchIcon(event);
            case TARGET_TAG:
                return getTagIcon(event);
            case TARGET_PROJECT_USER:
                return getProjectUserIcon(event);
            case TARGET_PROJECT_BOT:
                return getProjectBotIcon(event);
            default:
                return "";
        }
    }

}
