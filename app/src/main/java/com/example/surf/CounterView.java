package com.example.surf;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CounterView extends RelativeLayout implements View.OnClickListener {

  private int minValue;
  private int maxValue;

//  private ImageButton btnMinus;
//  private ImageButton btnPlus;
  private TextView counter;
  private OnCounterClickListener clickListener = null;

  public CounterView(Context context) {
    this(context, null);
  }

  public CounterView(Context context, AttributeSet attrs) {
    super(context, attrs);
    inflate(context, R.layout.counter_layout, this);
    init();
    applyAttrs(context, attrs);
  }

  @Override
  public void onClick(View v) {
    if (clickListener != null) {
      clickListener.onClick();
    }
    switch (v.getId()) {
      case R.id.btn_minus: {
        decrementValue();
        break;
      }
      case R.id.btn_plus: {
        incrementValue();
      }
    }
  }

  public int getValue() {
    return Integer.parseInt((String) counter.getText());
  }

  public void setValue(int value) {
    if (value < minValue) {
      value = minValue;
    } else if (value > maxValue) {
      value = maxValue;
    }
    counter.setText(String.valueOf(value));
  }

  public void incrementValue() {
    incrementValue(1);
  }

  public void incrementValue(int i) {
    setValue(getValue() + i);
  }

  public void decrementValue() {
    decrementValue(1);
  }

  public void decrementValue(int i) {
    setValue(getValue() - i);
  }

  private void init() {
    findViewById(R.id.btn_minus).setOnClickListener(this);
    findViewById(R.id.btn_plus).setOnClickListener(this);
    counter = (TextView) findViewById(R.id.counter);
  }

  private void applyAttrs(Context context, AttributeSet attrs) {
    TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CounterView, 0, 0);
    minValue = a.getInteger(R.styleable.CounterView_min_value, 0);
    maxValue = a.getInteger(R.styleable.CounterView_max_value, 100);
    a.recycle();
  }

  public void setOnCounterClickListener(OnCounterClickListener clickListener) {
    this.clickListener = clickListener;
  }

  public int getMaxValue() {
    return maxValue;
  }

  public void setMaxValue(int maxValue) {
    this.maxValue = maxValue;
  }

  public int getMinValue() {
    return minValue;
  }

  public void setMinValue(int minValue) {
    this.minValue = minValue;
  }

  public interface OnCounterClickListener {
    void onClick();
  }
}
