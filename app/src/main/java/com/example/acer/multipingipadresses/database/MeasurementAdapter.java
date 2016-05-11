package com.example.acer.multipingipadresses.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.acer.multipingipadresses.database.models.Measurement;
import com.example.acer.multipingipadresses.database.models.Host;
import com.example.acer.multipingipadresses.database.models.Measurement;
import com.example.acer.multipingipadresses.database.models.Model;

import java.util.ArrayList;
import java.util.List;

public class MeasurementAdapter extends Adapter {
    public static final String TABLE_NAME = "measurement";
    public static final String _ID = "id";
    public static final String MEASUREMENT_TYPE = "measurementType";
    public static final String CREATE_TABLE = "CREATE TABLE " +
            TABLE_NAME + " (" +
            _ID + " INTEGER PRIMARY KEY," +
            MEASUREMENT_TYPE + " TEXT" +
            " )";

    public MeasurementAdapter(Context context) {
        super(context);
    }

    @Override
    public void insert(Model model) {
        Measurement measurement = (Measurement) model;
        ContentValues values = new ContentValues();
        values.put(MEASUREMENT_TYPE, measurement.getMeasurementType());

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
    public Measurement findById(int id) {
        String[] projection = {
                _ID,
                MEASUREMENT_TYPE};

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
            Measurement meas = new Measurement();
            meas.setId(c.getInt(c.getColumnIndex(_ID)));
            meas.setMeasurementType(c.getString(c.getColumnIndex(MEASUREMENT_TYPE)));
            return meas;
        }
        return null;
    }

    public List<Measurement> findListBySampleId(int sampleId) {
        String[] projection = {
                _ID,
                MEASUREMENT_TYPE};

        String sortOrder =
                _ID + " DESC";

        Cursor c = db.query(
                TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                _ID + "=?",                         // The columns for the WHERE clause
                new String[]{String.valueOf(sampleId)},   // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        c.moveToFirst();
        List<Measurement> measList = new ArrayList<>();
        while (!c.isLast()) {
            Measurement meas = new Measurement();
            meas.setId(c.getInt(c.getColumnIndex(_ID)));
            meas.setMeasurementType(c.getString(c.getColumnIndex(MEASUREMENT_TYPE)));
            measList.add(meas);

        }
        return measList;
    }

    public ArrayList<Measurement> getAllRecords() {
//        db = DBHelper.ReadDatabase();

        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        ArrayList<Measurement> measurementArrayList = new ArrayList<Measurement>();
        Measurement device;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                device = new Measurement();
                device.setId(cursor.getInt(cursor.getColumnIndex(_ID)));
                device.setMeasurementType(cursor.getString(cursor.getColumnIndex(MEASUREMENT_TYPE)));
                measurementArrayList.add(device);
            }
        }
//        cursor.close();
//        db.close();
        return measurementArrayList;
    }
}
