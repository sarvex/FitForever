package com.fitforever.utility.ailment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sarvex Jatasra on 9/16/2015.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
  private final List<Fragment> fragments = new ArrayList<>();
  private final List<String> titles = new ArrayList<>();

  public PagerAdapter(FragmentManager fm) {
    super(fm);
  }

  @Override
  public Fragment getItem(int position) {
    return fragments.get(position);
  }

  @Override
  public CharSequence getPageTitle(int position) {
    return titles.get(position);
  }

  @Override
  public int getCount() {
    return fragments.size();
  }

  public void addFragment(Fragment fragment, String title) {
    fragments.add(fragment);
    titles.add(title);
  }
}
