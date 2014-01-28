package com.deveo.android.core;

import java.util.Map;

public class Project extends Base {

    private String id;

    private String name;

    private String description;

    private String color;

    private Map<String, String> avatar;

    private String visibility;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Map<String, String> getAvatar() {
        return avatar;
    }

    public void setAvatar(Map<String, String> avatar) {
        this.avatar = avatar;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

}
