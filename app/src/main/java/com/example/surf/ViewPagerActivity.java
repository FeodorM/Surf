package com.example.surf;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ViewPagerActivity extends AppCompatActivity {

  ExamplePageAdapter adapter;

  public static void start(Context context) {
    Intent intent = new Intent(context, ViewPagerActivity.class);
    context.startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_view_pager);

    ViewPager pager = (ViewPager) findViewById(R.id.view_pager);
    adapter = new ExamplePageAdapter(getSupportFragmentManager());
    pager.setAdapter(adapter);
    TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
    tabLayout.setupWithViewPager(pager);
    /*pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      String TAG = "PageChangeListener";
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.i(TAG, "onPageScrolled");
      }

      @Override
      public void onPageSelected(int position) {
        Log.i(TAG, "onPageSelected");
      }

      @Override
      public void onPageScrollStateChanged(int state) {
        Log.i(TAG, "onPageScrollStateChanged");
      }
    });*/

  }
}
