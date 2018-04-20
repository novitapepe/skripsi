package com.example.novi.cb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.novi.cb.adapter.RecyclerViewAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Details extends AppCompatActivity {


    int sensorType;
    RecyclerView rv;
    RecyclerViewAdapter adapter;

    DatabaseReference refLdr;
    DatabaseReference refSwitch;
    DatabaseReference refSuhu;

    List<String> listLdr, listSwitch, listSuhu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        sensorType = Integer.parseInt(getIntent().getStringExtra("key"));
        setTitle("Rincian Perubahan");

        initial();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (sensorType == 0) {
            refLdr.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    listLdr.clear();
                    for (DataSnapshot snapshotLdr: dataSnapshot.getChildren()) {
                        listLdr.add(snapshotLdr.getValue().toString());
                    }
                    setRecyclerData(listLdr);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        } else if (sensorType == 1) {
            refSwitch.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshotSwitch: dataSnapshot.getChildren()) {
                        listSwitch.add(snapshotSwitch.getValue().toString());
                    }
                    setRecyclerData(listSwitch);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        } else if (sensorType == 2){
            refSuhu.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshotSuhu: dataSnapshot.getChildren()) {
                        listSuhu.add(snapshotSuhu.getValue().toString());
                    }
                    setRecyclerData(listSuhu);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

    }

    public void initial (){
        listLdr = new ArrayList<>();
        listSuhu = new ArrayList<>();
        listSwitch = new ArrayList<>();

        refLdr = FirebaseDatabase.getInstance().getReference("ldr");
        refSwitch = FirebaseDatabase.getInstance().getReference("switch");
        refSuhu = FirebaseDatabase.getInstance().getReference("adc");

        rv = (RecyclerView) findViewById(R.id.lvDetails);
    }

    public void setRecyclerData(List<String> sensorVal) {
        adapter = new RecyclerViewAdapter(this, sensorType, sensorVal);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }
}
