package com.cermati.githubuser;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.cermati.githubuser.adapter.UserAdapter;
import com.cermati.githubuser.listener.EndlessRecyclerViewScrollListener;
import com.cermati.githubuser.model.User;
import com.cermati.githubuser.model.UserList;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.activity_main_recyler_view)
    RecyclerView userRecyclerView;
    @BindView(R.id.activity_main_fsv_search)
    FloatingSearchView searchView;
    @BindView(R.id.apiLimitError)
    TextView apiLimitTextView;
    @BindView(R.id.no_user)
    TextView noUserTextView;

    boolean apiLimit = false;

    private UserAdapter userAdapter;
    private List<User> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        userRecyclerView.setLayoutManager(layoutManager);
        userRecyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                getData();
            }
        });
        getData();
    }

    private void getData() {
        searchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {
                try {
                    if (!newQuery.isEmpty()) {
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("https://api.github.com/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        GithubInterface service = retrofit.create(GithubInterface.class);
                        service.getUserData(newQuery).enqueue(new Callback<UserList>() {
                            @Override
                            public void onResponse(Call<UserList> call, Response<UserList> response) {
                                if (response.code() != 403) {
                                    try {
                                        users = response.body().getItems();
                                        if (!users.isEmpty()) {
                                            userAdapter = new UserAdapter(getApplicationContext(), users);
                                            userRecyclerView.setVisibility(View.VISIBLE);
                                            userRecyclerView.setAdapter(userAdapter);
                                            userAdapter.notifyDataSetChanged();
                                        } else {
                                            searchView.clearQuery();
                                            searchView.clearSearchFocus();
                                            userRecyclerView.setVisibility(View.GONE);
                                            Toast.makeText(MainActivity.this, "No User Found", Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                } else {
                                    searchView.clearQuery();
                                    searchView.clearSearchFocus();
                                    Toast.makeText(MainActivity.this, "Api rate limit exeded", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(@NonNull Call<UserList> call, @NonNull Throwable t) {
                                Log.d("cekfailure", t.toString());
                                Toast.makeText(MainActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();

                            }
                        });

                    } else {
                        searchView.clearQuery();
                        searchView.clearSearchFocus();
                        userRecyclerView.setVisibility(View.GONE);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
    }

}
