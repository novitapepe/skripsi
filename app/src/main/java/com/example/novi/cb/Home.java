package com.example.novi.cb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.novi.cb.preference.PreferenceHelper;

public class Home extends AppCompatActivity {

    PreferenceHelper prefHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);

        Button _btnADaftar, _btnAMasuk;

        prefHelper = new PreferenceHelper(Home.this);

        _btnADaftar = (Button) findViewById(R.id.btnADaftar);
        _btnAMasuk = (Button) findViewById(R.id.btnAMasuk);

        _btnADaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intenta = new Intent(Home.this, Daftar.class);
                startActivity(intenta);
            }
        });
        _btnAMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = prefHelper.getUser();

                if (user == null) {
                    Intent intentb = new Intent(Home.this, Login.class);
                    startActivity(intentb);
                } else {
                    startActivity(new Intent(Home.this, MainActivity.class));
                }

            }
        });
    }
}

