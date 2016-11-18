package com.example.surf;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ButtonsActivity extends AppCompatActivity {

  private TextView textView;
  private RadioButton radioButton1;
  private CheckBox checkBox2;

  public static void start(Context context) {
    Intent intent = new Intent(context, ButtonsActivity.class);
    context.startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_buttons);

    textView = (TextView) findViewById(R.id.textView);
    radioButton1 = (RadioButton) findViewById(R.id.radio_1);
    checkBox2 = (CheckBox) findViewById(R.id.checkbox2);
    ((RadioGroup) findViewById(R.id.radioGroup)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
          case R.id.radio_1: {
            textView.setText("1");
            break;
          }
          case R.id.radio_2: {
            textView.setText("2");
            break;
          }
          case R.id.radio_3: {
            textView.setText("3");
            break;
          }
        }
      }
    });

    ((CounterView) findViewById(R.id.a_counter)).setOnCounterClickListener(new CounterView.OnCounterClickListener() {
      private int c = 0;
      @Override
      public void onClick() {
        c++;
        textView.setText("counter called " + c + " times");
      }
    });

    ((CheckBox) findViewById(R.id.checkbox1)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        textView.setText(buttonView.getText() + " is now " + (isChecked ? "checked" : "unchecked"));
      }
    });
  }


  public void checkSomething(View view) {
    radioButton1.setChecked(true);
    checkBox2.toggle();
  }
}
