package com.example.novi.cb.sqlite;

import com.example.novi.cb.model.DataDetails;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SHelp {
    private DatabaseReference mSensorRef;
    public SHelp(){
        mSensorRef = FirebaseDatabase.getInstance().getReference();
    }
    public void create(String title, String Content){
        String key = mSensorRef.getKey();


    }
}

