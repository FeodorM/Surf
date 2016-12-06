package com.example.surf;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ExamplePageAdapter extends FragmentPagerAdapter {

  private final String[] tabTitles = new String[] {"First", "Second", "Third", "Fourth", "Fifth"};

  public ExamplePageAdapter(FragmentManager fm) {
    super(fm);
  }

  @Override
  public Fragment getItem(int position) {
    return SimpleFragment.newInstance(String.valueOf(position + 1));
  }

  @Override
  public int getCount() {
    return 5;
  }

  @Override
  public CharSequence getPageTitle(int position) {
    return tabTitles[position];
  }
}
