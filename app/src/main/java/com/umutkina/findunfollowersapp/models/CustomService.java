package com.umutkina.findunfollowersapp.models;

import com.twitter.sdk.android.core.Callback;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by mac on 04/11/15.
 */
public interface CustomService {

    @GET("/1.1/followers/list.json")
                         void show(@Query("user_id") Long userId, @Query("screen_name") String
                var, @Query("skip_status") Boolean var1, @Query("include_user_entities") Boolean var2, @Query("count") Integer var3, Callback<Followers> cb);

    @GET("/1.1/friends/ids.json")
    void userIdList(@Query("cursor") long cursor, @Query("screen_name") String s, @Query("count") int count,Callback<Ids> cb);

}
