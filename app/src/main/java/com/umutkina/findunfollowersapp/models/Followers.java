package com.umutkina.findunfollowersapp.models;

import com.google.gson.annotations.SerializedName;
import com.twitter.sdk.android.core.models.User;

import java.util.List;

/**
 * Created by mac on 04/11/15.
 */
public class Followers {
    @SerializedName("users")
    public final List<User> users;

    public Followers(List<User> users) {
        this.users = users;
    }
}



