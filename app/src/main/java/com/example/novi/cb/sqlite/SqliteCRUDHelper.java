package com.example.novi.cb.sqlite;

import android.annotation.TargetApi;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.icu.text.RelativeDateTimeFormatter;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.widget.Toast;

import com.example.novi.cb.DatabaseHelper;
import com.example.novi.cb.model.DataDetails;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SqliteCRUDHelper {

    DatabaseHelper dbconfig;
    SQLiteDatabase db;
    Cursor cursor;
    Activity context;
    List<DataDetails> arraySensor;

    public SqliteCRUDHelper(Activity context) {
        this.context = context;

        init();
    }

    private void init() {
        dbconfig = new DatabaseHelper(context);
        arraySensor = new ArrayList<>();
    }

    public List<DataDetails> getDetailSensor(){
        try{

            db = dbconfig.getReadableDatabase();
            cursor = db.rawQuery("Select * From TBSENSOR", null);

            arraySensor.clear();
            if(cursor.getCount() != 0){
                cursor.moveToFirst();
                for(int i = 0 ; i < cursor.getCount() ; i++){
                    cursor.moveToPosition(i);

                    arraySensor.add(new DataDetails(cursor.getString(1),cursor.getString(2), cursor.getString(3)));
                }

            }
        }catch (SQLiteException e){
            Toast.makeText(context, "Error: " + e , Toast.LENGTH_SHORT).show();
        }
        return arraySensor;
    }

    public void addSensor(String sensor, String value){
        try{
            //SimpleDateFormat formatter = new SimpleDateFormat("dd HH:mm");
            Date date = new Date();
            String time="08:00";

            db = dbconfig.getWritableDatabase();
            db.execSQL("Insert into TBSENSOR (sensor, value, time) values('"+ sensor + "','"+ value +"','"+ time +"')");

        }catch(SQLiteException e ){
            Toast.makeText(context, "Error: "+e , Toast.LENGTH_SHORT).show();
        }
    }

}
