
package com.cermati.githubuser.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserList {

    @SerializedName("total_count") @Expose private Integer totalCount;
    @SerializedName("incomplete_results") @Expose private String incompleteResults;
    @SerializedName("items") @Expose private List<User> items;
    @SerializedName("message") @Expose private String message;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }


    public List<User> getItems() {
        return items;
    }

    public void setItems(List<User> items) {
        this.items = items;
    }

    public String getIncompleteResults() {
        return incompleteResults;
    }

    public void setIncompleteResults(String incompleteResults) {
        this.incompleteResults = incompleteResults;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
