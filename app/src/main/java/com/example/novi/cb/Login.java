package com.example.novi.cb;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.novi.cb.preference.PreferenceHelper;

public class Login extends AppCompatActivity {
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    Button _btnMasuk;
    EditText _euser, _epass;
    Cursor cursor;

    PreferenceHelper prefHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");

        openHelper = new DatabaseHelper(this);
        db=openHelper.getReadableDatabase();
        prefHelper = new PreferenceHelper(Login.this);

        _epass = (EditText) findViewById(R.id.epass);
        _euser = (EditText) findViewById(R.id.euser);
        _btnMasuk = (Button) findViewById(R.id.btnMasuk);


        _btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = _euser.getText().toString();
                String password = _epass.getText().toString();
                cursor = db.rawQuery("SELECT * FROM "+ DatabaseHelper.TABLE_NAME + " WHERE " + DatabaseHelper.COL_5 + "=? AND " + DatabaseHelper.COL_6 + "=?", new String[]{username,password});
                if(cursor!=null){
                    if(cursor.getCount()>0){
                        cursor.moveToNext();
                        prefHelper.addUser(_euser.getText().toString());

                        Toast.makeText(getApplicationContext(), "Login Sukses", Toast.LENGTH_LONG).show();
                        Intent intentc = new Intent(Login.this, MainActivity.class);
                        startActivity(intentc);
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(),"Username dan Password Salah", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }
}

