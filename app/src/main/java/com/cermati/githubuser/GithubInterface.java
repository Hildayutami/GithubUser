package com.cermati.githubuser;

import com.cermati.githubuser.model.User;
import com.cermati.githubuser.model.UserList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Nur Hildayanti Utami on 15/08/2017.
 * feel free to contact me on nur.hildayanti.u@gmail.com
 */

public interface GithubInterface {
    @GET("/search/users")
    Call<UserList> getUserData(@Query("q") String nameQuery);
}
