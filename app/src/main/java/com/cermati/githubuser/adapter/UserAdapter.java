package com.cermati.githubuser.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cermati.githubuser.R;
import com.cermati.githubuser.model.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Nur Hildayanti Utami on 15/08/2017.
 * feel free to contact me on nur.hildayanti.u@gmail.com
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private List<User> mUser;
    private Context context;

    public UserAdapter(Context context, List<User> users) {
        this.mUser = users;
        this.context = context;
    }

    //involves inflating row user layout and returning the holder
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        //inflate the username custom layout
        View userView = inflater.inflate(R.layout.row_user, parent, false);

        //return a new holder instance\
        return new ViewHolder(userView);
    }

    //populating data into the row item through holder
    @Override
    public void onBindViewHolder(UserAdapter.ViewHolder holder, int position) {
        User user = mUser.get(position);
        //set data which is retrieved from data model to the item view
        holder.userName.setText(user.getUsername());
        Glide.with(context).load(R.mipmap.drawable_default_profile_picture)
                .into(holder.userProfilePicture);
        if (!user.getAvatarUrl().isEmpty())
            Glide.with(context).load(user.getAvatarUrl())
                    .into(holder.userProfilePicture);

    }

    @Override
    public int getItemCount() {
        return mUser.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.row_user_tv_name)
        TextView userName;
        @BindView(R.id.row_user_iv_image)
        ImageView userProfilePicture;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }


}
