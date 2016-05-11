package com.example.acer.multipingipadresses.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.acer.multipingipadresses.database.models.Model;
import com.example.acer.multipingipadresses.database.models.Object;

import java.util.ArrayList;

public class ObjectAdapter extends Adapter {
    public static final String TABLE_NAME = "object";
    public static final String _ID = "id";
    public static final String IP_ADRESS = "ipAdress";
    public static final String DESCRIP = "descrip";
    public static final String INFO = "info";
    public static final String ADRESS = "adress";
    public static final String HOST_ID = "hostId";
    public static final String DEVICE_ID = "deviceId";
    public static final String CREATE_TABLE = "CREATE TABLE " +
            TABLE_NAME + " (" +
            _ID + " INTEGER PRIMARY KEY," +
            IP_ADRESS + " TEXT, " +
            DESCRIP + " TEXT, " +
            ADRESS + " TEXT, " +
            INFO + " TEXT, " +
            HOST_ID + " INTEGER, " +
            DEVICE_ID + " INTEGER" +
            " )";

    public ObjectAdapter(Context context) {
        super(context);
    }

    @Override
    public void insert(Model model) {
        Object probe = (Object) model;
        ContentValues values = new ContentValues();
        values.put(IP_ADRESS, probe.getIpAddress());
        values.put(DESCRIP, probe.getDescrip());
        values.put(INFO, probe.getInfo());
        values.put(ADRESS, probe.getAdress());
        values.put(HOST_ID, probe.getHostTypeId());
        values.put(DEVICE_ID, probe.getDeviceTypeId());

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
        Log.e("ObjectAdapter", "delete = " + id);
    }

    @Override
    public Object findById(int id) {
        String[] projection = {
                _ID,
                IP_ADRESS,
                DESCRIP,
                INFO,
                ADRESS,
                HOST_ID,
                DEVICE_ID};

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
            Object object = new Object();
            object.setId(c.getInt(c.getColumnIndex(_ID)));
            object.setIpAddress(c.getString(c.getColumnIndex(IP_ADRESS)));
            object.setDescrip(c.getString(c.getColumnIndex(DESCRIP)));
            object.setInfo(c.getString(c.getColumnIndex(INFO)));
            object.setAdress(c.getString(c.getColumnIndex(ADRESS)));
            object.setHostTypeId(c.getInt(c.getColumnIndex(HOST_ID)));
            object.setDeviceTypeId(c.getInt(c.getColumnIndex(DEVICE_ID)));
            return object;
        }
        return null;
    }

    public ArrayList<Object> getAllRecords() {
//        db = DBHelper.ReadDatabase();

        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        ArrayList<Object> objectArrayList = new ArrayList<Object>();
        Object object;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                object = new Object();
                object.setId(cursor.getInt(cursor.getColumnIndex(_ID)));
                object.setIpAddress(cursor.getString(cursor.getColumnIndex(IP_ADRESS)));
                object.setDescrip(cursor.getString(cursor.getColumnIndex(DESCRIP)));
                object.setAdress(cursor.getString(cursor.getColumnIndex(ADRESS)));
                object.setInfo(cursor.getString(cursor.getColumnIndex(INFO)));
                object.setHostTypeId(cursor.getInt(cursor.getColumnIndex(HOST_ID)));
                object.setDeviceTypeId(cursor.getInt(cursor.getColumnIndex(DEVICE_ID)));

                objectArrayList.add(object);
            }
        }
//        cursor.close();
//        db.close();
        return objectArrayList;
    }
}
