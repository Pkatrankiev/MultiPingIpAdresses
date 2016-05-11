package com.example.acer.multipingipadresses.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.example.acer.multipingipadresses.database.models.Model;
import com.example.acer.multipingipadresses.database.models.Sample;
import com.example.acer.multipingipadresses.database.models.User;

import java.util.ArrayList;

public class UserAdapter extends Adapter {
    public static final String TABLE_NAME = "user";
    public static final String _ID = "id";
    public static final String USER_NAME = "userName";
    public static final String USER_PASS = "userPass";
    public static final String USER_TYPE = "userType";
    public static final String CREATE_TABLE = "CREATE TABLE " +
            TABLE_NAME + " (" +
            _ID + " INTEGER PRIMARY KEY," +
            USER_NAME + " TEXT, " +
            USER_PASS + " TEXT, " +
            USER_TYPE + " TEXT" +
            " )";

    public UserAdapter(Context context) {
        super(context);
    }

    @Override
    public void insert(Model model) {
        User probe = (User) model;
        ContentValues values = new ContentValues();
        values.put(USER_NAME, probe.getUserName());
        values.put(USER_PASS, probe.getUserPass());
        values.put(USER_TYPE, probe.getUserType());


        db.insert(
                TABLE_NAME,
                "null",
                values);
    }

    @Override
    public void delete(int id) {
        String selection = _ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(id)};
        db.delete(TABLE_NAME, selection, selectionArgs);
    }

    @Override
    public User findById(int id) {
        String[] projection = {
                _ID,
                USER_NAME,
                USER_PASS,
                USER_TYPE};

        String sortOrder =
                _ID + " DESC";

        Cursor c = db.query(
                TABLE_NAME,                                // The table to query
                projection,                               // The columns to return
                _ID + "=?",                                // The columns for the WHERE clause
                new String[]{String.valueOf(id)},          // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        c.moveToFirst();
        if (c.getCount() > 0) {
            User user = new User();
            user.setId(c.getInt(c.getColumnIndex(_ID)));
            user.setUserName(c.getString(c.getColumnIndex(USER_NAME)));
            user.setUserPass(c.getString(c.getColumnIndex(USER_PASS)));
            user.setUserType(c.getString(c.getColumnIndex(USER_TYPE)));
            return user;
        }
        return null;
    }

    public ArrayList<User> getAllRecords() {
//        db = DBHelper.ReadDatabase();

        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        ArrayList<User> userArrayList = new ArrayList<User>();
        User user;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex(_ID)));
                user.setUserName(cursor.getString(cursor.getColumnIndex(USER_NAME)));
                user.setUserPass(cursor.getString(cursor.getColumnIndex(USER_PASS)));
                user.setUserType(cursor.getString(cursor.getColumnIndex(USER_TYPE)));
                userArrayList.add(user);
            }
        }
//        cursor.close();
//        db.close();
        return userArrayList;
    }
}
