package com.example.surf;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.surf.loader.MyAsyncTaskLoader;

import java.util.List;

public class NetworkActivity extends AppCompatActivity implements View.OnClickListener {

  private List<String> data;

  private LoaderManager.LoaderCallbacks<List<String>> loaderCallbacks
          = new LoaderManager.LoaderCallbacks<List<String>>() {
    @Override
    public Loader<List<String>> onCreateLoader(int id, Bundle args) {
      return new MyAsyncTaskLoader(NetworkActivity.this);
    }

    @Override
    public void onLoadFinished(Loader<List<String>> loader, List<String> dataFromLoader) {
      data = dataFromLoader;
      ListView listView = (ListView) findViewById(R.id.list_view);
      ArrayAdapter<String> adapter = new ArrayAdapter<String>(
              NetworkActivity.this, android.R.layout.simple_list_item_1, data);
      listView.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(Loader<List<String>> loader) {

    }
  };

  public static void start(Context context) {
    Intent intent = new Intent(context, NetworkActivity.class);
    context.startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_network);

    findViewById(R.id.update_btn).setOnClickListener(this);

    getSupportLoaderManager().initLoader(0, null, loaderCallbacks);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.update_btn: {
        if (data != null) {

        }
        break;
      }
    }
  }
}
