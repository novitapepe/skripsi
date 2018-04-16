package com.example.novi.cb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="register.db";
    public static final String TABLE_NAME="register";
    public static final String COL_1="ID";
    public static final String COL_2="NamaKos";
    public static final String COL_3="NamaPemilik";
    public static final String COL_4="Alamat";
    public static final String COL_5="User";
    public static final String COL_6="Pass";
    public static final String COL_7="Telpon";

    public static final String CREATE_TDSENSOR="CREATE TABLE TBSENSOR(ID INTEGER PRIMARY KEY AUTOINCREMENT,sensor TEXT, value  TEXT, time TEXT)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TDSENSOR);
        db.execSQL("CREATE TABLE "+ TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,NamaKos TEXT,NamaPemilik TEXT,Alamat TEXT,User TEXT,Pass TEXT,Telpon TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST"+TABLE_NAME);
        onCreate(db);

    }
}