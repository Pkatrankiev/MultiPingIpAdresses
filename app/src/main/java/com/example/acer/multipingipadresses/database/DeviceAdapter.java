package com.example.acer.multipingipadresses.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.acer.multipingipadresses.database.models.Device;
import com.example.acer.multipingipadresses.database.models.Model;

import java.util.ArrayList;
import java.util.List;

public class DeviceAdapter extends Adapter {
    public static final String TABLE_NAME = "device";
    public static final String _ID = "id";
    public static final String DEVICE_TYPE = "deviceType";
    public static final String CREATE_TABLE = "CREATE TABLE " +
            TABLE_NAME + " (" +
            _ID + " INTEGER PRIMARY KEY," +
            DEVICE_TYPE + " TEXT" +
            " )";

    public DeviceAdapter(Context context) {
        super(context);
    }


    @Override
    public void insert(Model model) {
//        db = DBHelper.ReadDatabase();

        Device probe = (Device) model;
        ContentValues values = new ContentValues();
        values.put(DEVICE_TYPE, probe.getDeviceType());

        db.insert(
                TABLE_NAME,
                "null",
                values);
//        db.close();
    }

    @Override
    public void delete(int id) {
//        db = DBHelper.ReadDatabase();
        String selection = _ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(id)};
        db.delete(TABLE_NAME, selection, selectionArgs);
//        db.close();
    }

    public void deleteDevice( Device device) {
//        db = DBHelper.ReadDatabase();
        db.delete(
                TABLE_NAME,
                _ID + " = ?",
                new String[]{String.valueOf(device.getId())}
        );
//        db.close();
    }

    @Override
    public Device findById(int id) {
//        db = DBHelper.ReadDatabase();

        String[] projection = {
                _ID,
                DEVICE_TYPE};

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
        Device device = new Device();
        if (c.getCount() > 0) {
            device.setId(c.getInt(c.getColumnIndex(_ID)));
            device.setDeviceType(c.getString(c.getColumnIndex(DEVICE_TYPE)));
        } else device = null;
//        c.close();
//        db.close();
        return device;
    }

    public Device findByDevType(String type) {
//        db = DBHelper.ReadDatabase();

        String[] projection = {
                _ID,
                DEVICE_TYPE};

        String sortOrder =
                _ID + " DESC";

        Cursor c = db.query(
                TABLE_NAME,                                // The table to query
                projection,                               // The columns to return
                DEVICE_TYPE + "=?",                                // The columns for the WHERE clause
                new String[]{String.valueOf(type)},          // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );
        c.moveToFirst();
        Device device = new Device();
        if (c.getCount() > 0) {
            device.setId(c.getInt(c.getColumnIndex(_ID)));
            device.setDeviceType(c.getString(c.getColumnIndex(DEVICE_TYPE)));
        } else device = null;
//        c.close();
//        db.close();
        return device;
    }

    public List<Device> getAllRecords() {
//        db = DBHelper.ReadDatabase();

        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        ArrayList<Device> deviceArrayList = new ArrayList<Device>();
        Device device;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                device = new Device();
                device.setId(cursor.getInt(cursor.getColumnIndex(_ID)));
                device.setDeviceType(cursor.getString(cursor.getColumnIndex(DEVICE_TYPE)));
                deviceArrayList.add(device);
            }
        }
//        cursor.close();
//        db.close();
        return deviceArrayList;
    }

}

