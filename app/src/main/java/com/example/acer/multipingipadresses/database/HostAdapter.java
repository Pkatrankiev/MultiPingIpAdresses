package com.example.acer.multipingipadresses.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.acer.multipingipadresses.database.models.Host;
import com.example.acer.multipingipadresses.database.models.Model;

import java.util.ArrayList;

/**
 * Created by Acer on 30.4.2016 г..
 */
public class HostAdapter  extends Adapter {
    public static final String TABLE_NAME = "host";
    public static final String _ID = "id";
    public static final String HOST_TYPE = "hostType";
    public static final String CREATE_TABLE = "CREATE TABLE " +
            TABLE_NAME + " (" +
            _ID + " INTEGER PRIMARY KEY," +
            HOST_TYPE + " TEXT" +
            " )";

    public HostAdapter(Context context) {
        super(context);
    }

    @Override
    public void insert(Model model) {
        Host probe = (Host) model;
        ContentValues values = new ContentValues();
        values.put(HOST_TYPE, probe.getHostType());

        db.insert(
                TABLE_NAME,
                "null",
                values);
    }

    @Override
    public void delete(int id) {
        String selection = _ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(id) };
        db.delete(TABLE_NAME, selection, selectionArgs);
    }

    @Override
    public Host findById(int id) {
        String[] projection = {
                _ID,
                HOST_TYPE};

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
            Host host = new Host();
            host.setId(c.getInt(c.getColumnIndex(_ID)));
            host.setHostType(c.getString(c.getColumnIndex(HOST_TYPE)));
            return host;
        }
        return null;
    }


    public Host findByHosType(String hostType) {
        String[] projection = {
                _ID,
                HOST_TYPE};

        String sortOrder =
                _ID + " DESC";

        Cursor c = db.query(
                TABLE_NAME,                                // The table to query
                projection,                               // The columns to return
                HOST_TYPE + "=?",                                // The columns for the WHERE clause
                new String[]{String.valueOf(hostType)},          // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );
        c.moveToFirst();
        if (c.getCount() > 0) {
            Host host = new Host();
            host.setId(c.getInt(c.getColumnIndex(_ID)));
            host.setHostType(c.getString(c.getColumnIndex(HOST_TYPE)));
            return host;
        }
        return null;
    }

    public ArrayList<Host> getAllRecords() {
//        db = DBHelper.ReadDatabase();

        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        ArrayList<Host> hostArrayList = new ArrayList<Host>();
        Host host;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                host = new Host();
                host.setId(cursor.getInt(cursor.getColumnIndex(_ID)));
                host.setHostType(cursor.getString(cursor.getColumnIndex(HOST_TYPE)));
                hostArrayList.add(host);
            }
        }
//        cursor.close();
//        db.close();
        return hostArrayList;
    }

}
