package com.example.tugaspraktikum12_muhamadazizprasetyo_2110511095_b.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.tugaspraktikum12_muhamadazizprasetyo_2110511095_b.model.Member;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "db_my_jaya_app";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_STD = "members";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_NIM = "nim";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_GROUP_TO = "group_to";
    private static final String KEY_APP_NAME = "app_name";

    private static final String QUERY_CREATE_TABLE = "CREATE TABLE " + TABLE_STD + " ("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_NAME + " TEXT NOT NULL, "
            + KEY_NIM + " TEXT NOT NULL, "
            + KEY_EMAIL + " TEXT NOT NULL, "
            + KEY_GROUP_TO + " TEXT NOT NULL, "
            + KEY_APP_NAME + " TEXT NOT NULL);";

    private static final String CREATE_TABLE_MEMBERS = QUERY_CREATE_TABLE;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MEMBERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_STD + "'");
        onCreate(db);
    }

    public long addUserDetail(String name, String nim, String email, String groupTo, String appName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_NIM, nim);
        values.put(KEY_EMAIL, email);
        values.put(KEY_GROUP_TO, groupTo);
        values.put(KEY_APP_NAME, appName);
        long insert = db.insert(TABLE_STD, null, values);
        return insert;
    }

    @SuppressLint("Range")
    public ArrayList<Member> getAllUsers() {
        ArrayList<Member> userModelArrayList = new ArrayList<Member>();
        String selectQuery = "SELECT * FROM " + TABLE_STD;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                Member std = new Member();
                std.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                std.setName(c.getString(c.getColumnIndex(KEY_NAME)));
                std.setNim(c.getString(c.getColumnIndex(KEY_NIM)));
                std.setEmail(c.getString(c.getColumnIndex(KEY_EMAIL)));
                std.setGroupTo(c.getString(c.getColumnIndex(KEY_GROUP_TO)));
                std.setAppName(c.getString(c.getColumnIndex(KEY_APP_NAME)));
                // adding to Students list
                userModelArrayList.add(std);
            } while (c.moveToNext());
        }
        return userModelArrayList;
    }

    public void deleteUser(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STD, KEY_ID + " = ?", new String[]{String.valueOf(id)});
    }

    public int updateUser(int id, String name, String nim, String email, String groupTo, String appName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_NIM, nim);
        values.put(KEY_EMAIL, email);
        values.put(KEY_GROUP_TO, groupTo);
        values.put(KEY_APP_NAME, appName);
        return db.update(TABLE_STD, values, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }
}
