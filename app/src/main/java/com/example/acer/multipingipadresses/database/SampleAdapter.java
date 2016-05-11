package com.example.acer.multipingipadresses.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.acer.multipingipadresses.database.models.Model;
import com.example.acer.multipingipadresses.database.models.Sample;

import java.util.ArrayList;
import java.util.List;

public class SampleAdapter extends Adapter {
    public static final String TABLE_NAME = "sample";
    public static final String _ID = "id";
    public static final String OBJECT_ID = "objectId";
    public static final String MEASUREMENT_TYPE_ID = "measurementTypeId";
    public static final String MEASUREMENT_VALUE = "measVal";
    public static final String TIME_MEASUREMENT = "timeMeasurement";
    public static final String CREATE_TABLE = "CREATE TABLE " +
            TABLE_NAME + " (" +
            _ID + " INTEGER PRIMARY KEY, " +
            OBJECT_ID + " INTEGER, " +
            MEASUREMENT_TYPE_ID + " INTEGER, " +
            MEASUREMENT_VALUE + " INTEGER, " +
            TIME_MEASUREMENT + " TEXT" +
            " )";

    public SampleAdapter(Context context) {
        super(context);
    }

    @Override
    public void insert(Model model) {
        Sample probe = (Sample) model;
        ContentValues values = new ContentValues();
        values.put(OBJECT_ID, probe.getObjectId());
        values.put(MEASUREMENT_TYPE_ID, probe.getMeasurementTypeId());
        values.put(MEASUREMENT_VALUE, probe.getMeasurementValue());
        values.put(TIME_MEASUREMENT, probe.getTimeMeasurement());

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
    public Sample findById(int id) {
        String[] projection = {
                _ID,
                OBJECT_ID,
                MEASUREMENT_TYPE_ID,
                MEASUREMENT_VALUE,
                TIME_MEASUREMENT};

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
            Sample sample = new Sample();
            sample.setId(c.getInt(c.getColumnIndex(_ID)));
            sample.setObjectId(c.getInt(c.getColumnIndex(OBJECT_ID)));
            sample.setMeasurementTypeId(c.getInt(c.getColumnIndex(MEASUREMENT_TYPE_ID)));
            sample.setMeasurementValue(c.getInt(c.getColumnIndex(MEASUREMENT_VALUE)));
            sample.setTimeMeasurement(c.getString(c.getColumnIndex(TIME_MEASUREMENT)));
            return sample;
        }
        return null;
    }

    public List<Sample> aaa(int objectId) {
        String[] projection = {
                _ID,
                OBJECT_ID,
                MEASUREMENT_TYPE_ID,
                MEASUREMENT_VALUE,
                TIME_MEASUREMENT};

        String sortOrder =
                _ID + " DESC";

        Cursor c = db.query(
                TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                OBJECT_ID + "=?",                         // The columns for the WHERE clause
                new String[]{String.valueOf(objectId)},   // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        c.moveToFirst();

        List<Sample> sampleArrayList = new ArrayList<>();
        while (!c.isLast()) {
            Log.e("-cccccccccc-----", " cursor position=  " + c.getPosition());
            Sample sample = new Sample();
            sample.setId(c.getInt(c.getColumnIndex(_ID)));
            sample.setObjectId(c.getInt(c.getColumnIndex(OBJECT_ID)));
            sample.setMeasurementTypeId(c.getInt(c.getColumnIndex(MEASUREMENT_TYPE_ID)));
            sample.setMeasurementValue(c.getInt(c.getColumnIndex(MEASUREMENT_VALUE)));
            sample.setTimeMeasurement(c.getString(c.getColumnIndex(TIME_MEASUREMENT)));
            sampleArrayList.add(sample);
            c.moveToNext();
        }
        return sampleArrayList;
    }


    public ArrayList<Sample> findListByObjectId(int objectId) {


        ArrayList<Sample> sampleArrayList = new ArrayList<>();
        Cursor c =
                db.query(true, TABLE_NAME, new String[]{
                                _ID,
                                OBJECT_ID,
                                MEASUREMENT_TYPE_ID,
                                MEASUREMENT_VALUE,
                                TIME_MEASUREMENT},
                        OBJECT_ID + "=?",
                        new String[]{String.valueOf(objectId)},
                        null, null, null, null);


        if (c.moveToFirst()) {
            do {
                Log.e("SampleAdapter", " cursor position=  " + c.getPosition());
                Sample sample = new Sample();
                sample.setId(c.getInt(c.getColumnIndex(_ID)));
                sample.setObjectId(c.getInt(c.getColumnIndex(OBJECT_ID)));
                sample.setMeasurementTypeId(c.getInt(c.getColumnIndex(MEASUREMENT_TYPE_ID)));
                sample.setMeasurementValue(c.getInt(c.getColumnIndex(MEASUREMENT_VALUE)));
                sample.setTimeMeasurement(c.getString(c.getColumnIndex(TIME_MEASUREMENT)));
                sampleArrayList.add(sample);
            } while (c.moveToNext());
        }
        if (c != null && !c.isClosed()) {
            c.close();
        }
        return sampleArrayList;


    }


    public ArrayList<Sample> getAllRecords() {
//        db = DBHelper.ReadDatabase();

        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        ArrayList<Sample> sampleArrayList = new ArrayList<Sample>();
        Sample sample;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                sample = new Sample();
                sample.setId(cursor.getInt(cursor.getColumnIndex(_ID)));
                sample.setMeasurementTypeId(cursor.getInt(cursor.getColumnIndex(MEASUREMENT_TYPE_ID)));
                sample.setMeasurementValue(cursor.getInt(cursor.getColumnIndex(MEASUREMENT_VALUE)));
                sample.setTimeMeasurement(cursor.getString(cursor.getColumnIndex(TIME_MEASUREMENT)));
                sampleArrayList.add(sample);
            }
        }
//        cursor.close();
//        db.close();
        return sampleArrayList;
    }
}
