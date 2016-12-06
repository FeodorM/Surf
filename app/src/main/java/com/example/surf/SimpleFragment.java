package com.example.surf;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SimpleFragment extends Fragment {

  private static final String TAG = "com.example.surf.SimpleFragment.text";

  public static SimpleFragment newInstance(String text) {
    SimpleFragment simpleFragment = new SimpleFragment();
    Bundle args = new Bundle();
    args.putString(TAG, text);
    simpleFragment.setArguments(args);
    return simpleFragment;
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.simple_fragment_layout, container, false);
    String text = getArguments().getString(TAG);
    ((TextView) view.findViewById(R.id.fragment_text_view)).setText(text);
    return view;
  }
}