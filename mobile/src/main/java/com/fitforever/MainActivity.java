package com.fitforever;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.fitforever.ailment.PagerAdapter;
import com.fitforever.alert.AlertFragment;
import com.fitforever.ask.AskFragment;
import com.fitforever.auth.LoginActivity;
import com.fitforever.doctor.DoctorFragment;
import com.fitforever.history.HistoryFragment;
import com.fitforever.home.HomeFragment;
import com.fitforever.packages.PackagesFragment;
import com.fitforever.profile.ProfileFragment;
import com.fitforever.search.SearchFragment;
import com.fitforever.wallet.WalletFragment;
import com.parse.ParseAnalytics;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity
    implements HomeFragment.OnFragmentInteractionListener, SearchFragment.OnFragmentInteractionListener,
    ProfileFragment.OnFragmentInteractionListener, AskFragment.OnFragmentInteractionListener,
    AlertFragment.OnFragmentInteractionListener, WalletFragment.OnFragmentInteractionListener,
    HistoryFragment.OnFragmentInteractionListener, PackagesFragment.OnFragmentInteractionListener,
    DoctorFragment.OnFragmentInteractionListener {

  private DrawerLayout drawerLayout;
  private Toolbar toolbar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    toolbar = (Toolbar) findViewById(R.id.toolbar);
    toolbar.setTitle(R.string.fit_forever);
    setSupportActionBar(toolbar);

    final ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
      actionBar.setDisplayHomeAsUpEnabled(true);
    }

    drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    if (navigationView != null) {
      navigationView.setNavigationItemSelectedListener(
          new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
              menuItem.setChecked(true);
              drawerLayout.closeDrawers();
              return true;
            }
          });
    }

    ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
    if (viewPager != null) {
      PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), this);
      adapter.addFragment(new HomeFragment(), "Home");
      adapter.addFragment(new WalletFragment(), "Wallet");
      adapter.addFragment(new PackagesFragment(), "Packages");
      adapter.addFragment(new DoctorFragment(), "Doctors");
      adapter.addFragment(new HistoryFragment(), "History");
      viewPager.setAdapter(adapter);
    }

    TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
    tabLayout.setupWithViewPager(viewPager);
    tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

    ParseAnalytics.trackAppOpenedInBackground(getIntent());
    if (ParseUser.getCurrentUser() == null) {
      toLoginActivity();
    }
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    MenuItem searchItem = menu.findItem(R.id.action_search);

    SearchManager searchManager = (SearchManager) MainActivity.this.getSystemService(Context.SEARCH_SERVICE);

    SearchView searchView = null;
    if (searchItem != null) {
      searchView = (SearchView) searchItem.getActionView();
    }
    if (searchView != null) {
      searchView.setSearchableInfo(searchManager.getSearchableInfo(MainActivity.this.getComponentName()));
    }
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(final MenuItem item) {
    final int id = item.getItemId();
    boolean result = false;

    switch (id) {
      case R.id.action_logout:
        ParseUser.logOutInBackground();
        toLoginActivity();
        result = true;
        break;
      case R.id.action_settings:
        result = true;
        break;
      case android.R.id.home:
        drawerLayout.openDrawer(GravityCompat.START);
        result = true;
      default:
        break;
    }

    return result || super.onOptionsItemSelected(item);
  }

  private void toLoginActivity() {
    startActivity(new Intent(this, LoginActivity.class)
        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
  }

  @Override
  public void onFragmentInteraction(Uri uri) {

  }
}
