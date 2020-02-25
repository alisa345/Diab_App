package com.alisa.diabet.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DbHelperAvatar extends SQLiteOpenHelper {

    private static final String DB_NAME = "DB_avatar";
    private static final int DB_VER = 1;
    public static final String DB_TABLE = "ACCOUNTS";
    public static final String DB_COLUMN = "Avatar";

    byte[] accAva;


    public DbHelperAvatar(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = String.format("CREATE TABLE %s (ID INTEGER PRIMARY KEY AUTOINCREMENT,%s BLOB);", DB_TABLE, DB_COLUMN);
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = String.format("DELETE TABLE IF EXISTS %s", DB_TABLE);
        db.execSQL(query);
        onCreate(db);

    }

    public void insertImg(byte[] image) throws SQLiteException {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DB_COLUMN, image);
        db.insert(DB_TABLE, null, values);
        db.close();
    }

    public byte[] getAvatarList() {
        ArrayList<String> avatarList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DB_TABLE, new String[]{DB_COLUMN}, null, null, null, null, null);
        while (cursor.moveToNext()) {
            accAva = cursor.getBlob(0);
        }
        cursor.close();
        db.close();
        return accAva;
    }

    public void deleteAvatar() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DB_TABLE, null, null);
    }


}
