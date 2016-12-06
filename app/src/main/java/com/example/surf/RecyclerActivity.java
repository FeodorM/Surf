package com.example.surf;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.LinkedList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity implements View.OnClickListener {

  private MoviesAdapter adapter;
  private List<Movie> moviesList = new LinkedList<>();
  private RecyclerView recyclerView;

  private EditText editTextTitle;
  private EditText editTextYear;

  public static void start(Context context) {
    Intent intent = new Intent(context, RecyclerActivity.class);
    context.startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_recycler);

    editTextTitle = (EditText) findViewById(R.id.editTextTitle);
    editTextYear = (EditText) findViewById(R.id.editTextYear);

    ((Button) findViewById(R.id.addMovieBtn)).setOnClickListener(this);
    ((Button) findViewById(R.id.clearListBtn)).setOnClickListener(this);

    moviesList.add(new Movie("Test 1", "15"));
    moviesList.add(new Movie("Test 2", "16"));

    recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
    recyclerView.setLayoutManager(layoutManager);
    adapter = new MoviesAdapter(moviesList);
    recyclerView.setAdapter(adapter);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.addMovieBtn: {
        String title = editTextTitle.getText().toString();
        String year = editTextYear.getText().toString();
        if (!title.isEmpty() && !year.isEmpty()) {
          moviesList.add(0, new Movie(title, year));
          adapter.notifyItemInserted(0);
          recyclerView.scrollToPosition(0);
          break;
        }
      }
      case R.id.clearListBtn: {
        moviesList.clear();
        adapter.notifyDataSetChanged();
        break;
      }
    }
  }
}
