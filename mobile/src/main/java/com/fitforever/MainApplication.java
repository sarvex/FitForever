package com.fitforever;

import android.app.Application;

/**
 * Created by Sarvex on 9/15/2015.
 */
public class MainApplication extends Application {
  Parse.enableLocalDatastore(this);

  Parse.initialize(this, BuildConfig.PARSE_APP_ID, BuildConfig.PARSE_CLIENT_KEY);
}
