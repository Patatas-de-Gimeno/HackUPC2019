package com.example.hackupc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ProductosDbHelper extends android.database.sqlite.SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Productos.db";

    public ProductosDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + ProductoContract.ProductoEntry.TABLE_NAME + " ("
                +  ProductoContract.ProductoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +  ProductoContract.ProductoEntry.ID + " TEXT NOT NULL,"
                +  ProductoContract.ProductoEntry.NAME + " TEXT NOT NULL,"
                +  ProductoContract.ProductoEntry.TYPE + "TEXT NOT NULL,"
                +  ProductoContract.ProductoEntry.PRICE + "TEXT NOT NULL,"
                + "UNIQUE (" +  ProductoContract.ProductoEntry.ID + "))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
