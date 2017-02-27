package com.vgilanza.app.vigilanza.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by Aswin on 27-Jan-17.
 */
public class SQLiteHandler extends SQLiteOpenHelper {
    private static final String TAG=SQLiteHandler.class.getSimpleName();

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="android_api";
    private static final String TABLE_USER="user";//Login Table

    //Each column in Login table
    private static final String KEY_ID="id";
    private static final String KEY_NAME ="name";
    private static final String KEY_EMAIL ="email";
    private static final String KEY_UID ="uid";
    private static final String KEY_CREATED_AT ="created_at";
    private static final String KEY_MOBILE_NO ="mobile_no";
    private static final String KEY_RECOVERY_NO ="recovery_no";

    public SQLiteHandler(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    //Create tables

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOGIN_TABLE = "CREATE_TABLE "+TABLE_USER+"("+KEY_ID+" INTEGER PRIMARY KEY,"+KEY_NAME+" TEXT,"+KEY_EMAIL+" TEXT UNIQUE,"+KEY_UID+" TEXT,"+KEY_CREATED_AT+" TEXT,"+KEY_MOBILE_NO+" INTEGER UNIQUE,"+KEY_RECOVERY_NO+" INTEGER"+")";
        db.execSQL(CREATE_LOGIN_TABLE);
        Log.d(TAG,"Database tables created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_USER);
        onCreate(db);//Or create table again
    }

    public void addUser(String name,String email,String uid,String created_at,String mobile_no,String recovery_no){//Storing details
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_NAME,name);
        values.put(KEY_EMAIL,email);
        values.put(KEY_UID,uid);
        values.put(KEY_CREATED_AT,created_at);
        values.put(KEY_MOBILE_NO,mobile_no);
        values.put(KEY_RECOVERY_NO,recovery_no);

        //Inserting row
        long id=db.insert(TABLE_USER,null,values);
        db.close();
        Log.d(TAG,"New USer inserted into sqlite: "+id);
    }
    //Geting user data from database
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user=new HashMap<String,String>();
        String selectQuery="SELECT * FROM "+TABLE_USER;

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);

        cursor.moveToFirst();//move to first row
        if(cursor.getCount()>0){
            user.put("name",cursor.getString(2));
            user.put("email",cursor.getString(3));
            user.put("uid",cursor.getString(4));
            user.put("created_at",cursor.getString(7));
            user.put("mobile_no",cursor.getString(9));
            user.put("recovery_no",cursor.getString(10));
        }
        cursor.close();
        db.close();
        Log.d(TAG,"Fetching user from sqlite: "+user.toString());
        return user;
    }

    // delete users
    public void deleteUsers(){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_USER,null,null);
        db.close();
        Log.d(TAG,"Deleted all user info from sqlite");
    }
}
