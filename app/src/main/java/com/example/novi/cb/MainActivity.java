package com.example.novi.cb;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.system.ErrnoException;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.novi.cb.model.Sensor;
import com.example.novi.cb.preference.PreferenceHelper;
import com.example.novi.cb.sqlite.SqliteCRUDHelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    DatabaseReference refLdr;
    DatabaseReference refSwitch;
    DatabaseReference refTemp;
    FirebaseDatabase fbDatabase;
    SqliteCRUDHelper helper ;

    Boolean updateSwitch = false;
    Boolean updateLdr = false;
    Boolean updateSuhu = false;

    TextView tldr,tsuhu,tswitch;

    List<Sensor> listSensors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Beranda");

        initial();

        refLdr = FirebaseDatabase.getInstance().getReference("ldr");
        refSwitch = fbDatabase.getReference();
        refTemp = FirebaseDatabase.getInstance().getReference("adc");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_beranda, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public void showAbout(){

        View v = getLayoutInflater().inflate(R.layout.about_me, null);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert
                .setView(v)
                .setCancelable(false)
                .setNegativeButton("Tutup", null)
                .show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert
                .setTitle("Keluar")
                .setMessage("Keluar dari beranda sensor")
                .setCancelable(false)
                .setPositiveButton("Keluar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PreferenceHelper prefHelper = new PreferenceHelper(MainActivity.this);
                        prefHelper.deleteUser();
                        finish();
                    }
                })
                .setNegativeButton("Batal", null)
                .show();


        return true;
    }

    private void initial() {
        fbDatabase = FirebaseDatabase.getInstance();
        listSensors = new ArrayList<>();

        tldr = (TextView) findViewById(R.id.cv1);
        tsuhu = (TextView) findViewById(R.id.cv3);
        tswitch = (TextView) findViewById(R.id.cv2);

        helper = new SqliteCRUDHelper(this);
    }

    public void onLightClick(View v){

        Intent i = new Intent(this, Details.class);
        i.putExtra("key","0");
        startActivity(i);
    }
    public void onDoorClick(View v){
        Intent i = new Intent(this, Details.class);
        i.putExtra("key","1");
        startActivity(i);
    }
    public void onTempClick(View v){
        Intent i = new Intent(this, Details.class);
        i.putExtra("key","2");
        startActivity(i);
    }

    public void onAboutClick(View v){
        showAbout();
    }

    @Override
    protected void onPause() {
        super.onPause();

        updateSwitch = false;
        updateLdr = false;
        updateSuhu = false;
    }


    @Override
    protected void onStart() {
        super.onStart();

        refSwitch.child("switch").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> listSwitch = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    listSwitch.add(snapshot.getValue().toString());
                }
                tswitch.setText(listSwitch.get(listSwitch.size() - 1));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        refLdr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> listLdr = new ArrayList<>();
                for (DataSnapshot snapshotLdr: dataSnapshot.getChildren()) {
                    listLdr.add(snapshotLdr.getValue().toString());
                }
                tldr.setText(listLdr.get(listLdr.size() - 1));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        refTemp.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> listTemp = new ArrayList<>();
                for (DataSnapshot snapshotTemp: dataSnapshot.getChildren()) {
                    listTemp.add(snapshotTemp.getValue().toString());
                }
                tsuhu.setText(listTemp.get(listTemp.size() - 1) + " °C");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
