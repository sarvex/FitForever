package com.fitforever;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Sarvex on 9/15/2015.
 */
public class MainApplication extends Application {
  @Override
  public void onCreate() {
    super.onCreate();

    Parse.enableLocalDatastore(this);

    Parse.initialize(this, BuildConfig.PARSE_APP_ID, BuildConfig.PARSE_CLIENT_KEY);
  }
}
