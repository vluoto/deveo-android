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

public class EventUtils {

    public static String toMessage(Event event) {
        String msg = "";
        String subject = event.getSubject();
        String operation = event.getOperation();

        switch (event.getTarget()) {
            case Event.TARGET_REPOSITORY:
                msg = String.format("%s %s repository %s", subject, operation, event.getRepository());
                break;
            case Event.TARGET_PUSH:
                msg = String.format("%s %s a new push with %s commit(s) to %s", subject, operation, event.getCommitCount(), event.getRepository());
                break;
            case Event.TARGET_BRANCH:
                msg = String.format("%s %s branch %s at %s", subject, operation, event.getRef(), event.getRepository());
                break;
            case Event.TARGET_TAG:
                msg = String.format("%s %s tag %s at %s", subject, operation, event.getRef(), event.getRepository());
                break;
            case Event.TARGET_PROJECT_USER:
                switch (event.getOperation()) {
                    case Event.OPERATION_CREATED:
                        msg = String.format("%s added %s as %s to the project", subject, event.getUser(), event.getRole());
                        break;
                    case Event.OPERATION_UPDATED:
                        msg = String.format("%s gave %s %s rights to the project", subject, event.getUser(), event.getRole());
                        break;
                }
                break;
            case Event.TARGET_PROJECT_BOT:
                msg = String.format("%s %s project bot %s", subject, operation, event.getBot());
                break;
        }

        return msg;
    }

    public static String iconStringFor(Event event) {
        String str = "";
        String operation = event.getOperation();

        switch (event.getTarget()) {
            case Event.TARGET_REPOSITORY:
                switch (operation) {
                    case Event.OPERATION_CREATED:
                        str = TypefaceUtils.ICON_EVT_ADD_REPOSITORY;
                        break;
                    case Event.OPERATION_DELETED:
                        str = TypefaceUtils.ICON_EVT_REMOVE_REPOSITORY;
                        break;
                }
                break;
            case Event.TARGET_PUSH:
                switch (operation) {
                    case Event.OPERATION_CREATED:
                        str = TypefaceUtils.ICON_PUSH;
                        break;
                }
                break;
            case Event.TARGET_BRANCH:
                switch (operation) {
                    case Event.OPERATION_CREATED:
                        str = TypefaceUtils.ICON_EVT_ADD_BRANCH;
                        break;
                    case Event.OPERATION_DELETED:
                        str = TypefaceUtils.ICON_EVT_REMOVE_BRANCH;
                        break;
                }
                break;
            case Event.TARGET_TAG:
                switch (operation) {
                    case Event.OPERATION_CREATED:
                        str = TypefaceUtils.ICON_EVT_ADD_TAG;
                        break;
                    case Event.OPERATION_DELETED:
                        str = TypefaceUtils.ICON_EVT_REMOVE_TAG;
                        break;
                }
                break;
            case Event.TARGET_PROJECT_USER:
                switch (operation) {
                    case Event.OPERATION_CREATED:
                        str = TypefaceUtils.ICON_EVT_ADD_USER;
                        break;
                    case Event.OPERATION_UPDATED:
                        str = TypefaceUtils.ICON_EVT_REMOVE_USER;
                        break;
                    case Event.OPERATION_DELETED:
                        str = TypefaceUtils.ICON_EVT_REMOVE_USER;
                        break;
                }
                break;
            case Event.TARGET_PROJECT_BOT:
                switch (operation) {
                    case Event.OPERATION_CREATED:
                        str = TypefaceUtils.ICON_EVT_ADD_BOT;
                        break;
                    case Event.OPERATION_DELETED:
                        str = TypefaceUtils.ICON_EVT_REMOVE_BOT;
                        break;
                }
                break;
        }

        return str;
    }

}
