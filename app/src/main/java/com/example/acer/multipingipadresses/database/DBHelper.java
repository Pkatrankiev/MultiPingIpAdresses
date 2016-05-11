package com.example.acer.multipingipadresses.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "database";
    private static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DeviceAdapter.CREATE_TABLE);
        db.execSQL(HostAdapter.CREATE_TABLE);
        db.execSQL(MeasurementAdapter.CREATE_TABLE);
        db.execSQL(ObjectAdapter.CREATE_TABLE);
        db.execSQL(SampleAdapter.CREATE_TABLE);
        db.execSQL(UserAdapter.CREATE_TABLE);

    }

    public SQLiteDatabase ReadDatabase() {

        SQLiteDatabase db = this.getReadableDatabase();
        return db;
    }

    public SQLiteDatabase WritDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
