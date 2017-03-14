package com.example.surf;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.surf.db.DatabaseHelper;
import com.example.surf.db.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotificationsActivity extends AppCompatActivity implements View.OnClickListener {

  private final List<User> userList = new ArrayList<>();
  private ArrayAdapter<User> adapter;
  private EditText editText;
  private DatabaseHelper databaseHelper;

  public static void start(Context context) {
    Intent intent = new Intent(context, NotificationsActivity.class);
    context.startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_notifications);

    findViewById(R.id.progress_notification_btn).setOnClickListener(this);
    findViewById(R.id.another_notification_btn).setOnClickListener(this);
    findViewById(R.id.wait_btn).setOnClickListener(this);
    findViewById(R.id.new_item_btn).setOnClickListener(this);
    findViewById(R.id.print_btn).setOnClickListener(this);
    editText = (EditText) findViewById(R.id.edit_text);

    databaseHelper = new DatabaseHelper(this);

    ListView listView = (ListView) findViewById(R.id.users_list);
    adapter = new ArrayAdapter<>(
            this,
            android.R.layout.simple_list_item_1,
            userList
    );
    listView.setAdapter(adapter);
    updateList();
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.progress_notification_btn: {
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.plus)
                .setContentTitle("Progress bar from app")
                .setContentText("Some text")
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setAutoCancel(true)
                .setProgress(0, 0, true);
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        final NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        new Thread(new Runnable() {
          @Override
          public void run() {
            for (int i = 0; i <= 100; i++) {
              builder.setProgress(100, i, false);
              manager.notify(0, builder.build());
              try {
                Thread.sleep(500);
              } catch (InterruptedException e) {
                Log.d("ProgressBar", "sleepFailure");
              }
            }
            builder
                    .setContentText("Complete")
                    .setProgress(0, 0, false);
            manager.notify(0, builder.build());
          }
        }).start();

        break;
      }
      case R.id.another_notification_btn: {
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        String[] events = new String[]{"One", "Two", "Three", "Four", "Five", "Six"};
        inboxStyle.setBigContentTitle("Events:");
        for (String event : events) {
          inboxStyle.addLine(event);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Notification from app")
                .setContentText("Some text")
                .setStyle(inboxStyle)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setAutoCancel(true);
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1, builder.build());
        break;
      }
      case R.id.wait_btn: {
        final Handler handler = new Handler() {
          @Override
          public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            String text = bundle.getString("BLA");
            Toast.makeText(NotificationsActivity.this, text, Toast.LENGTH_SHORT).show();
          }
        };

        Toast.makeText(NotificationsActivity.this, "Start", Toast.LENGTH_SHORT).show();
        new Thread(new Runnable() {
          @Override
          public void run() {
            long endTime = System.currentTimeMillis() + 20 * 1000;

            while (System.currentTimeMillis() < endTime) {
              synchronized (this) {
                try {
                  Bundle bundle = new Bundle();
                  bundle.putString("BLA", "It's working!");
                  Message msg = handler.obtainMessage();
                  msg.setData(bundle);
                  handler.sendMessage(msg);
                  wait(endTime - System.currentTimeMillis());
                } catch (Exception e) {
                  // pass
                }
              }
            }
          }
        }).start();
        break;
      }
      case R.id.new_item_btn: {
        try {
          User user = new User().setmName(editText.getText().toString());
          databaseHelper.getUserDao().create(user);
          updateList();
        } catch (SQLException e) {
          e.printStackTrace();
        }
        break;
      }
      case R.id.print_btn: {
        try {
          Dao<User, Integer> userDao = databaseHelper.getUserDao();
          User user = userDao.queryBuilder()
                  .where()
                  .lt(User.FIELD_NAME_ID, 10)
                  .query()
                  .get(0);
          GsonBuilder builder = new GsonBuilder();
          Gson gson = builder.create();
          String json = gson.toJson(user);
          Log.d("BLA", json);
          User u = gson.fromJson("{\"id\":1,\"name\":\"user\"}", User.class);
          Log.d("BLA1", "" + u);
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
  }

  private void updateList() {
    try {
      Dao<User, Integer> userDao = databaseHelper.getUserDao();
      final List<User> users = userDao.queryForAll();

      for (User user : users) {
        if (!userList.contains(user)) {
          userList.add(user);
        }
      }
      adapter.notifyDataSetChanged();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
