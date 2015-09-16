package com.fitforever;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.parse.Parse;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Sarvex on 9/15/2015.
 */
public class MainApplication extends Application {
  @Override
  public void onCreate() {
    super.onCreate();

    TwitterAuthConfig authConfig = new TwitterAuthConfig(BuildConfig.TWITTER_KEY, BuildConfig.TWITTER_SECRET);
    Fabric.with(this, new Crashlytics(), new Twitter(authConfig));

    Parse.enableLocalDatastore(this);

    Parse.initialize(this, BuildConfig.PARSE_APP_ID, BuildConfig.PARSE_CLIENT_KEY);
  }
}
