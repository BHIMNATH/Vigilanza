package com.vgilanza.app.vigilanza;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

/**
 * Created by Aswin on 25-Feb-17.
 */
public class LoginDataBaseAdapter {
    static final String DATABASE_NAME = "login.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;
    static final String DATABASE_CREATE = "create table " + "LOGIN" + "( " + "ID" + " integer primary key autoincrement," + "USERNAME  text,PASSWORD text,EMAIL text,MOBILE text,RECOVERY text ); ";
    public SQLiteDatabase db;
    private final Context context;
    private DataBaseHelper dbHelper;


    public LoginDataBaseAdapter(Context _context) {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null,
                DATABASE_VERSION);
    }

    public LoginDataBaseAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public SQLiteDatabase getDatabaseInstance() {
        return db;
    }

    public void insertEntry(String name, String password,String email,String mobile,String recovery) {
        ContentValues newValues = new ContentValues();
        newValues.put("USERNAME", name);
        newValues.put("PASSWORD", password);
        newValues.put("EMAIL", email);
        newValues.put("MOBILE", mobile);
        newValues.put("RECOVERY", recovery);
        db.insert("LOGIN", null, newValues);
    }
    public int deleteEntry(String UserName) {
        String where = "USERNAME=?";
        int numberOFEntriesDeleted = db.delete("LOGIN", where,
                new String[] { UserName });
        return numberOFEntriesDeleted;
    }

    public String getSinlgeEntry(String email) {
        Cursor cursor = db.query("LOGIN", null, " EMAIL=?",
                new String[] { email }, null, null, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;
    }

    public void updateEntry(String userName, String password) {
        ContentValues updatedValues = new ContentValues();
        updatedValues.put("USERNAME", userName);
        updatedValues.put("PASSWORD", password);

        String where = "USERNAME = ?";
        db.update("LOGIN", updatedValues, where, new String[] { userName });
    }
}
