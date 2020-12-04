package com.zamzamsaefulbahtiar.utsmp.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "boardTerbilang.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "board";
    public static final String KOLOM_ID = "id";
    public static final String KOLOM_USERNAME = "username";
    public static final String KOLOM_SKOR = "skor";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createDB = "CREATE TABLE " + TABLE_NAME + " (" +
                KOLOM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KOLOM_USERNAME + " VARCHAR(100) NOT NULL UNIQUE, " +
                KOLOM_SKOR + " INTEGER NOT NULL)";
        sqLiteDatabase.execSQL(createDB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVer, int newVer) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
