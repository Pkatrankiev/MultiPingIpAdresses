package com.example.acer.multipingipadresses.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.acer.multipingipadresses.database.models.Model;

public abstract class Adapter {

    protected SQLiteDatabase db;

    public abstract void insert(Model model);
    public abstract void delete(int id);
    public abstract Model findById(int id);

    public Adapter(Context context) {
        DBHelper helper = new DBHelper(context);
        this.db = helper.getWritableDatabase();
    }


}
