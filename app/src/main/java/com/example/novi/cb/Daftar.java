package com.example.novi.cb;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Daftar extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button _btnDaftar, _btnHome;
    EditText _et1, _et2, _et3, _et4, _et5, _et6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
        setTitle("Daftar");

        openHelper=new DatabaseHelper(this);
        _btnDaftar = (Button)findViewById(R.id.btnDaftar);

        _et1 = (EditText)findViewById(R.id.et1);
        _et2 = (EditText)findViewById(R.id.et2);
        _et3 = (EditText)findViewById(R.id.et3);
        _et4 = (EditText)findViewById(R.id.et4);
        _et5 = (EditText)findViewById(R.id.et5);
        _et6 = (EditText)findViewById(R.id.et6);
        _btnDaftar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                db=openHelper.getWritableDatabase();
                String fet1 = _et1.getText().toString();
                String fet2 = _et2.getText().toString();
                String fet3 = _et3.getText().toString();
                String fet4 = _et4.getText().toString();
                String fet5 = _et5.getText().toString();
                String fet6 = _et6.getText().toString();
                insertdata(fet1, fet2, fet3, fet4, fet5, fet6);
                Toast.makeText(getApplicationContext(),"Daftar Sukses", Toast.LENGTH_LONG).show();

            }
        });


    }
    public void insertdata(String fet1, String fet2, String fet3, String fet4, String fet5, String fet6){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_2, fet1);
        contentValues.put(DatabaseHelper.COL_3, fet2);
        contentValues.put(DatabaseHelper.COL_4, fet3);
        contentValues.put(DatabaseHelper.COL_5, fet4);
        contentValues.put(DatabaseHelper.COL_6, fet5);
        contentValues.put(DatabaseHelper.COL_7, fet6);
        long id = db.insert(DatabaseHelper.TABLE_NAME, null, contentValues);

    }
}

