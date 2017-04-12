package com.vgilanza.app.vigilanza;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Aswin on 25-Feb-17.
 */
public class ContactDataBaseAdapter {
    static final String DATABASE_NAME = "contacts.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;
    final String CONTACT_NAME=" CONTACT_NAME=?";
    final String CONTACT_NO=" CONTACT_NAME=?";
    final String CONTACT_MAIL=" CONTACT_NAME=?";

    static final String DATABASE_CREATE = "create table " + "CONTACTS" + "( " + "ID" + " integer primary key autoincrement," + "CONTACT_NAME  text, CONTACT_NO  text,CONTACT_MAIL text ); ";
    public SQLiteDatabase db;
    private final Context context;
    private DataBaseHelper dbHelper;


    public ContactDataBaseAdapter(Context _context) {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null,
                DATABASE_VERSION);
    }

    public ContactDataBaseAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public SQLiteDatabase getDatabaseInstance() {
        return db;
    }

    public void insertEntry(String contact_name,String contact_no,String contact_mail) {
        ContentValues newValues = new ContentValues();
        newValues.put("CONTACT_NAME", contact_name);
        newValues.put("CONTACT_NO", contact_no);
        newValues.put("CONTACT_MAIL", contact_mail);
        db.insert("CONTACTS", null, newValues);
    }
    public int deleteEntry(String contact_name) {
        String where = "CONTACT_NAME=?";
        int numberOFEntriesDeleted = db.delete("CONTACTS", where,
                new String[]{contact_name});
        return numberOFEntriesDeleted;
    }

//    public ArrayList getAllContacts() {
//        ArrayList arrayList=new ArrayList();
//        Cursor res = db.rawQuery( "select * from CONTACTS", null );
//        res.moveToFirst();
//        while (!res.isAfterLast()){
//            arrayList.add(res.getString(res.getColumnIndex("CONTACT_NAME")));
//            res.moveToNext();
//        }
//        res.close();
//        return arrayList;
//    }

    public String getContacts(String contact) {

        Cursor cursor = db.query("CONTACTS", null, " CONTACT_NAME=?", new String[] { contact }, null, null, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String value = cursor.getString(cursor.getColumnIndex("CONTACT_NAME"));
        cursor.close();
        return value;
    }
    public String getContactNo(String contact_no) {
        Cursor cursor = db.query("CONTACTS", null, " CONTACT_NO=?",
                new String[] { contact_no }, null, null, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String contactNo = cursor.getString(cursor.getColumnIndex("CONTACT_NO"));
        cursor.close();
        return contactNo;
    }
    public String getContactMail(String contact_mail) {
        Cursor cursor = db.query("CONTACTS", null, " CONTACT_MAIL=?",
                new String[] { contact_mail }, null, null, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String contactMail = cursor.getString(cursor.getColumnIndex("CONTACT_MAIL"));
        cursor.close();
        return contactMail;
    }

    public void updateEntry(String contact_name, String contact_no) {
        ContentValues updatedValues = new ContentValues();
        updatedValues.put("CONTACT_NAME", contact_name);
        updatedValues.put("CONTACT_NO", contact_no);

        String where = "CONTACT_NAME = ?";
        db.update("CONTACTS", updatedValues, where, new String[] { contact_name });
    }
}
