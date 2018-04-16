package com.example.novi.cb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.novi.cb.adapter.RecyclerViewAdapter;

public class Details extends AppCompatActivity {


    String sensorType;
    RecyclerView rv;
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        sensorType = getIntent().getStringExtra("key");
        setTitle(sensorType);

        initial();

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

    }

    public void initial (){
        rv = (RecyclerView) findViewById(R.id.lvDetails);
        adapter = new RecyclerViewAdapter(this, Integer.parseInt(sensorType));

    }
}
