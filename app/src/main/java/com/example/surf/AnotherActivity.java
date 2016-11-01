package com.example.surf;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class AnotherActivity extends AppCompatActivity {

  private static final String TAG = "AnotherActivity";
  public static final String KEY = "AnotherActivity/someText";



  public static void start(Context context, String extra) {
    Intent intent = new Intent(context, AnotherActivity.class);
    intent.putExtra(AnotherActivity.KEY, extra);
    context.startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_another);

    Intent intent = getIntent();
    String text = intent.getStringExtra(AnotherActivity.KEY);
    Log.d(TAG, "onCreate with " + text);
  }
}
