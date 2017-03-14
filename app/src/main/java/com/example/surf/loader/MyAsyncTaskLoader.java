package com.example.surf.loader;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;

import java.util.ArrayList;
import java.util.List;

public class MyAsyncTaskLoader extends AsyncTaskLoader<List<String>> {

  private static final String PARAM = "param";
  private List<String> data;

  public static Bundle args(String param) {
    Bundle args = new Bundle();
    args.putString(PARAM, param);
    return args;
  }

  public MyAsyncTaskLoader(Context context) {
    super(context);
  }

  @Override
  protected void onStartLoading() {
    if (data != null) {
      deliverResult(data);
    } else {
      forceLoad();
    }
  }

  @Override
  public List<String> loadInBackground() {
    List<String> data = new ArrayList<>();
    for (int i = 0; i < 10; i ++) {
      data.add("String " + i);
    }
    return data;
  }

  @Override
  public void deliverResult(List<String> data) {
    this.data = data;
    super.deliverResult(data);
  }
}
