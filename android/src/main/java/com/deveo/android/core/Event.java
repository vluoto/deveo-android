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

package com.deveo.android.core;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Event extends Base {

    public static final String ATTR_PROJECT = "project";

    public static final String ATTR_REPOSITORY = "repository";

    public static final String ATTR_TARGET = "target";

    public static final String ATTR_SUBJECT = "subject";

    public static final String TARGET_REPOSITORY = "repository";

    public static final String TARGET_PUSH = "push";

    public static final String TARGET_BRANCH = "branch";

    public static final String TARGET_TAG = "tag";

    public static final String TARGET_PROJECT_USER = "project_user";

    public static final String TARGET_PROJECT_BOT = "project_bot";

    public static final String OPERATION_CREATED = "created";

    public static final String OPERATION_UPDATED = "updated";

    public static final String OPERATION_DELETED = "deleted";

    public static final String OPERATION_COMPLETED = "completed";

    public static final String OPERATION_FAILED = "failed";

    private String id;

    private User subject;

    private String operation;

    private String target;

    private Objects objects;

    private String timestamp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getSubject() {
        return subject;
    }

    public void setSubject(User subject) {
        this.subject = subject;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Objects getObjects() {
        return objects;
    }

    public void setObjects(Objects objects) {
        this.objects = objects;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getProject() {
        return objects.getProject();
    }

    public String getRepository() {
        return objects.getRepository();
    }

    public List<String> getCommits() {
        return objects.getCommits();
    }

    public List<String> getResources() {
        return objects.getResources();
    }

    public String getLastCommit() {
        return objects.getLastCommit();
    }

    public String getCommitCount() {
        return objects.getCommitCount();
    }

    public String getRef() {
        return objects.getRef();
    }

    public String getUser() {
        return objects.getUser();
    }

    public String getRole() {
        return objects.getRole();
    }

    public String getBot() {
        return objects.getBot();
    }

    private class Objects {

        /**
         * The ID of the project to which the event belongs to.
         */
        private String project;

        /**
         * The ID of the repository to which the event belongs to.
         */
        private String repository;

        /**
         * The commit identifier list of a push, branch, and build event.
         */
        private List<String> commits;

        /**
         * The resource list of a build event.
         */
        private List<String> resources;

        /**
         * The last commit of a push and branch event.
         */
        @SerializedName("last_commit")
        private String lastCommit;

        /**
         * The commit count of a push and branch event.
         */
        @SerializedName("commit_count")
        private String commitCount;

        /**
         * The describing identifier for a branch/tag of a push, branch, and tag event.
         */
        private String ref;

        /**
         * The build name of a build event.
         */
        private String name;

        /**
         * The ID of the targeted user in a project user event.
         */
        private String user;

        /**
         * The role of the targeted user in a project user event.
         */
        private String role;

        /**
         * The ID of the targeted bot in a project bot event.
         */
        private String bot;

        public String getProject() {
            return project;
        }

        public void setProject(String project) {
            this.project = project;
        }

        public String getRepository() {
            return repository;
        }

        public void setRepository(String repository) {
            this.repository = repository;
        }

        public List<String> getCommits() {
            return commits;
        }

        public void setCommits(List<String> commits) {
            this.commits = commits;
        }

        public List<String> getResources() {
            return resources;
        }

        public void setResources(List<String> resources) {
            this.resources = resources;
        }

        public String getLastCommit() {
            return lastCommit;
        }

        public void setLastCommit(String lastCommit) {
            this.lastCommit = lastCommit;
        }

        public String getCommitCount() {
            return commitCount;
        }

        public void setCommitCount(String commitCount) {
            this.commitCount = commitCount;
        }

        public String getRef() {
            return ref;
        }

        public void setRef(String ref) {
            this.ref = ref;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getBot() {
            return bot;
        }

        public void setBot(String bot) {
            this.bot = bot;
        }
    }

}
