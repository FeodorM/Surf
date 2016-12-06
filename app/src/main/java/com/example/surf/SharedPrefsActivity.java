package com.example.surf;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SharedPrefsActivity extends AppCompatActivity implements View.OnClickListener {

  public static final String MYPREFS = "com.example.surf.SharedPrefs";

  private static final String countPref = "count";
  private TextView textView;

  public static void start(Context context) {
    Intent intent = new Intent(context, SharedPrefsActivity.class);
    context.startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_shared_prefs);

    findViewById(R.id.minus_btn).setOnClickListener(this);
    findViewById(R.id.plus_btn).setOnClickListener(this);

    textView = (TextView) findViewById(R.id.textView);
    setCount(SharedPrefs.loadCountFromSharedPrefs(this));
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.minus_btn: {
        int count = getCurrentCount() - 1;
        SharedPrefs.saveCountToSharedPrefs(this, count);
        setCount(count);
        break;
      }
      case R.id.plus_btn: {
        int count = getCurrentCount() + 1;
        SharedPrefs.saveCountToSharedPrefs(this, count);
        setCount(count);
        break;
      }
    }
  }

  private int getCurrentCount() {
    return Integer.parseInt(textView.getText().toString());
  }

  private void setCount(int count) {
    textView.setText(String.valueOf(count));
  }

  private static class SharedPrefs {
    private static int loadCountFromSharedPrefs(Context context) {
      SharedPreferences prefs = context.getSharedPreferences(MYPREFS, MODE_PRIVATE);
      return prefs.getInt(countPref, 0);
    }

    private static void saveCountToSharedPrefs(Context context, int count) {
      SharedPreferences prefs = context.getSharedPreferences(MYPREFS, MODE_PRIVATE);
      SharedPreferences.Editor edit = prefs.edit();
      edit.putInt(countPref, count);
      edit.apply();
    }
  }
}
