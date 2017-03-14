package com.example.surf;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = "MainActivity";

  private TextView textView;
  private EditText editText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (savedInstanceState != null && savedInstanceState.containsKey(TAG)) {
      Log.d(TAG, savedInstanceState.getString(TAG));
    }

    textView = (TextView) findViewById(R.id.textView);
    editText = (EditText) findViewById(R.id.editText);

    Log.d(TAG, "onCreate");

    for (int i = 0; i < 30; i ++) {
      Log.d(TAG, getResources().getQuantityString(R.plurals.androids, i, i));
    }
  }

  @Override
  protected void onRestart() {
    super.onRestart();
    Log.d(TAG, "onRestart");
  }

  @Override
  protected void onStart() {
    super.onStart();
    Log.d(TAG, "onStart");
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    Log.d(TAG, "onSaveInstanceState");
    outState.putString(TAG, "saving instance state is correct");
  }

  @Override
  protected void onResume() {
    super.onResume();
    Log.d(TAG, "onResume");
  }

  @Override
  protected void onPause() {
    super.onPause();
    Log.d(TAG, "onPause");
  }

  @Override
  protected void onStop() {
    super.onStop();
    Log.d(TAG, "onStop");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    Log.d(TAG, "onDestroy");
  }

  public void next(View view) {
    AnotherActivity.start(MainActivity.this, "someDebugInfo");
  }

/*
  public void sendMessage(View view) {
    Intent sendIntent = new Intent();
    sendIntent.setAction(Intent.ACTION_SEND);
    sendIntent.putExtra(Intent.EXTRA_TEXT, "some text");
    sendIntent.setType("text/plain");

    if (sendIntent.resolveActivity(getPackageManager()) != null) {
      startActivity(sendIntent);
    }
  }
*/

  public void changeText(View view) {
    textView.setText(editText.getText());
  }

  public void goToButtons(View view) {
    ButtonsActivity.start(this);
  }

  public void goToRecyclerView(View view) {
    RecyclerActivity.start(this);
  }

  public void goToViewPager(View view) {
    ViewPagerActivity.start(this);
  }

  public void goToSharedPrefsActivity(View view) {
    SharedPrefsActivity.start(this);
  }

  public void goToNotifications(View view) {
    NotificationsActivity.start(this);
  }

  public void goToNetwork(View view) {
    NetworkActivity.start(this);
  }
}
