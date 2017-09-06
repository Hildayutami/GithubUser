
package com.cermati.githubuser.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("login")
    @Expose
    private String username;
    @SerializedName("id")
    @Expose
    private Integer usernameId;
    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUsernameId() {
        return usernameId;
    }

    public void setUsernameId(Integer usernameId) {
        this.usernameId = usernameId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
