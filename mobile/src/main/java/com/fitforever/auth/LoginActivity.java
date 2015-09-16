package com.fitforever.auth;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import com.fitforever.MainActivity;
import com.fitforever.R;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password and via Google+ sign in.
 * <p/>
 * ************ IMPORTANT SETUP NOTES: ************
 * In order for Google+ sign in to work with your app, you must first go to:
 * https://developers.google.com/+/mobile/android/getting-started#step_1_enable_the_google_api
 * and follow the steps in "Step 1" to create an OAuth 2.0 client for your package.
 */
public class LoginActivity extends AppCompatActivity {


  @Bind(R.id.username) EditText username;
  @Bind(R.id.password) EditText password;

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_login);
    ButterKnife.bind(this);
  }

  @Override
  public boolean onCreateOptionsMenu(final Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_login, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(final MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    final int id = item.getItemId();

    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @OnClick(R.id.button_login_sign_up)
  public void signUp(final View view) {
    startActivity(new Intent(this, SignUpActivity.class));
  }

  @OnClick(R.id.button_sign_in)
  public void signIn(final View view) {
    final String username = this.username.getText().toString().trim();
    final String password = this.password.getText().toString().trim();

    if (username.isEmpty()) {
      new AlertDialog.Builder(this).setTitle(R.string.error_title).setMessage(R.string.username_cannot_be_empty)
          .setPositiveButton(android.R.string.ok, null).create().show();
    } else if (password.isEmpty()) {
      new AlertDialog.Builder(this).setTitle(R.string.error_title).setMessage(R.string.password_cannot_be_empty)
          .setPositiveButton(android.R.string.ok, null).create().show();
    } else {
      setProgressBarIndeterminate(true);
      ParseUser.logInInBackground(username, password, new LogInCallback() {
        @Override
        public void done(final ParseUser user, final ParseException exception) {
          setProgressBarIndeterminate(false);
          if (exception == null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
          } else {
            new AlertDialog.Builder(LoginActivity.this).setTitle(R.string.error_title).setMessage(exception.getMessage())
                .setPositiveButton(android.R.string.ok, null).create().show();
          }
        }
      });
    }
  }
}

