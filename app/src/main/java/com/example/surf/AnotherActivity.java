package com.example.surf;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class AnotherActivity extends AppCompatActivity implements View.OnClickListener {

  private static final String TAG = "AnotherActivity";
  public static final String KEY = "AnotherActivity/someText";

  private TextView textView;

  public static void start(Context context, String extra) {
    Intent intent = new Intent(context, AnotherActivity.class);
    intent.putExtra(AnotherActivity.KEY, extra);
    context.startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_another);

    textView = (TextView) findViewById(R.id.textView);

    ImageButton imageButton = (ImageButton) findViewById(R.id.imageView);
    imageButton.setOnClickListener(this);

    Button button = (Button) findViewById(R.id.firstButton);
    button.setOnClickListener(this);

    button = (Button) findViewById(R.id.lastButton);
    button.setOnClickListener(this);

    Intent intent = getIntent();
    String text = intent.getStringExtra(AnotherActivity.KEY);
    Log.d(TAG, "onCreate with " + text);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.firstButton: {
        textView.setText("first");
        break;
      }
      case R.id.imageView: {
        textView.setText("image");
        break;
      }
      case R.id.lastButton: {
        textView.setText("last");
        break;
      }

    }
  }
}
