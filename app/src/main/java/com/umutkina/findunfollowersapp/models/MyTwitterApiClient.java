package com.umutkina.findunfollowersapp.models;

import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterSession;

/**
 * Created by mac on 04/11/15.
 */
public class MyTwitterApiClient extends TwitterApiClient {
    public MyTwitterApiClient(TwitterSession session) {
        super(session);
    }

    public CustomService getCustomService() {
        return getService(CustomService.class);
    }

}