package com.tiagogameover.speedtraffic.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHandler extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "user_database";
    private final static String TABLE_NAME = "userdata";
    private final static int DATABASE_VERSION = 1;
    private static final String COL_USER_ID = "user_id";
    private static final String USER_NAME = "name";
    private static final String PHONE = "contact_number";
    private static final String UPLOAD ="upload";
    private static final String DATA ="data";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COL_USER_ID + " INTEGER PRIMARY KEY," + USER_NAME + " TEXT," + UPLOAD + " TEXT," + DATA + " TEXT,"
                + PHONE + " TEXT UNIQUE" + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXITS" + TABLE_NAME);
        onCreate(db);

    }

    public void addUsers(ListDataModel userData) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_NAME, userData.getName());
        values.put(PHONE, userData.getPhone());
        values.put(UPLOAD, userData.getUpload());
        values.put(DATA, userData.getData());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<ListDataModel> getAllUsers() {
        List<ListDataModel> userList = new ArrayList<ListDataModel>();

        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ListDataModel data = new ListDataModel();
                data.setUser_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_USER_ID))));
                data.setName(cursor.getString(cursor.getColumnIndex(USER_NAME)));
                data.setPhone(cursor.getString(cursor.getColumnIndex(PHONE)));
                data.setUpload(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                data.setData(cursor.getString(cursor.getColumnIndex(DATA)));
                // Adding contact to list
                userList.add(data);
            } while (cursor.moveToNext());
        }

        return userList;
    }
    public void deleteUsers(ListDataModel userData){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME,COL_USER_ID + " = ?",
                new String[] { String.valueOf(userData.getUser_id()) });
        db.close();

    }
}
