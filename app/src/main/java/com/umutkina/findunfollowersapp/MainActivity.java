package com.umutkina.findunfollowersapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.umutkina.findunfollowersapp.models.CustomService;
import com.umutkina.findunfollowersapp.models.Ids;
import com.umutkina.findunfollowersapp.models.MyTwitterApiClient;

import java.util.ArrayList;

import io.fabric.sdk.android.Fabric;


public class MainActivity extends ActionBarActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "ho5LhHjJl0vM709lOlOjtohcE";
    private static final String TWITTER_SECRET = "YLIdTRwTRRonFuSgXRNOxcRCCyBlWm63SAM3CoNkXXdA6iF03Q";
    private TwitterLoginButton loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_main);
        loginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // The TwitterSession is also available through:
                // Twitter.getInstance().core.getSessionManager().getActiveSession()
                TwitterSession session = result.data;
                // TODO: Remove toast and use the TwitterSession's userID
                // with your app's user model
                String msg = "@" + session.getUserName() + " logged in! (#" + session.getUserId() + ")";


                MyTwitterApiClient myTwitterApiClient= new MyTwitterApiClient(session);
                CustomService customService = myTwitterApiClient.getCustomService();
//                customService.show(session.getUserId(),null,true,true,100,new Callback<Followers>() {
//                    @Override
//                    public void success(Result<Followers> followersResult) {
//                        Followers data = followersResult.data;
//                        List<User> users = data.users;
//                        User user = users.get(0);
//                        Toast.makeText(getApplicationContext(), user.name+" --"+users.size(), Toast.LENGTH_LONG).show();
//                    }
//
//                    @Override
//                    public void failure(TwitterException e) {
//
//                    }
//                });

                customService.userIdList(-1,session.getUserName(),5000,new Callback<Ids>() {
                    @Override
                    public void success(Result<Ids> idsResult) {
                        Ids data = idsResult.data;
                        ArrayList<Long> ids = data.getIds();
                        Long integer = ids.get(0);
                        Toast.makeText(getApplicationContext(), integer+"--", Toast.LENGTH_LONG).show();


                    }

                    @Override
                    public void failure(TwitterException e) {
e.printStackTrace();
                    }
                });

            }
            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Login with Twitter failure", exception);
            }
        });
        // TODO: Use a more specific parent
//        final ViewGroup parentView = (ViewGroup) getWindow().getDecorView().getRootView();
//        // TODO: Base this Tweet ID on some data from elsewhere in your app
//        long tweetId = 631879971628183552L;
//        TweetUtils.loadTweet(tweetId, new Callback<Tweet>() {
//            @Override
//            public void success(Result<Tweet> result) {
//                TweetView tweetView = new TweetView(MainActivity.this, result.data);
//                parentView.addView(tweetView);
//            }
//
//            @Override
//            public void failure(TwitterException exception) {
//                Log.d("TwitterKit", "Load Tweet failure", exception);
//            }
//        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Make sure that the loginButton hears the result from any
        // Activity that it triggered.
        loginButton.onActivityResult(requestCode, resultCode, data);
    }

}
