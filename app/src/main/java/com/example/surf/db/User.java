package com.example.surf.db;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = User.TABLE_NAME_USERS)
public class User {

  public static final String TABLE_NAME_USERS = "users";

  public static final String FIELD_NAME_ID = "id";
  public static final String FIELD_NAME_NAME = "name";

  @DatabaseField(columnName = FIELD_NAME_ID, generatedId = true)
  @SerializedName("id")
  private int mId;

  @DatabaseField(columnName = FIELD_NAME_NAME)
  @SerializedName("name")
  private String mName;

  @Override
  public String toString() {
    return mName + "(" + mId + ")";
  }

  public User() {
  }

  public int getmId() {
    return mId;
  }

  public User setmId(int mId) {
    this.mId = mId;
    return this;
  }

  public String getmName() {
    return mName;
  }

  public User setmName(String mName) {
    this.mName = mName;
    return this;
  }

  @Override
  public boolean equals(Object obj) {
    return this == obj || mId == ((User) obj).mId || super.equals(obj);

  }
}
