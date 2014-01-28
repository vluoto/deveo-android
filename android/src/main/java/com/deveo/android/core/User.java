package com.deveo.android.core;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class User extends Base {

    private String id;

    private String email;

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")
    private String lastName;

    @SerializedName("display_name")
    private String displayName;

    @SerializedName("company_admin")
    private boolean companyAdmin;

    @SerializedName("instance_admin")
    private boolean instanceAdmin;

    private String description;

    private String phone;

    private String title;

    private Map<String, String> avatar;

    @SerializedName("synchronized_fields")
    private List<String> synchronizedFields;

    private String locale;

    private String source;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isCompanyAdmin() {
        return companyAdmin;
    }

    public void setCompanyAdmin(boolean companyAdmin) {
        this.companyAdmin = companyAdmin;
    }

    public boolean isInstanceAdmin() {
        return instanceAdmin;
    }

    public void setInstanceAdmin(boolean instanceAdmin) {
        this.instanceAdmin = instanceAdmin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Map<String, String> getAvatar() {
        return avatar;
    }

    public void setAvatar(Map<String, String> avatar) {
        this.avatar = avatar;
    }

    public List<String> getSynchronizedFields() {
        return synchronizedFields;
    }

    public void setSynchronizedFields(List<String> synchronizedFields) {
        this.synchronizedFields = synchronizedFields;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

}
