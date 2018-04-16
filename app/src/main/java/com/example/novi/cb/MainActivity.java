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
import android.widget.Toast;

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

    FirebaseDatabase database;
    DatabaseReference refLdr;
    DatabaseReference refSwitch;
    DatabaseReference refTemp;



    ListView _lv;
    ArrayList<String> sensor = new ArrayList<>();
    String[] getSensor ;

    Boolean updateSwitch = false;
    Boolean updateLdr = false;
    Boolean updateSuhu = false;

    TextView tldr,tsuhu,tswitch;

    SqliteCRUDHelper helper ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Beranda");

        initial();

        refLdr = FirebaseDatabase.getInstance().getReference("ldr");
        refSwitch = FirebaseDatabase.getInstance().getReference("switch");
        refTemp = FirebaseDatabase.getInstance().getReference("adc");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_beranda, menu);

        return super.onCreateOptionsMenu(menu);
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
        Intent i = new Intent(this, Details.class);
        i.putExtra("key","about");
        startActivity(i);
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

        refSwitch.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> s = dataSnapshot.getChildren().
                tswitch.setText(dataSnapshot.getValue().toString());


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        refLdr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tldr.setText(dataSnapshot.getValue().toString());

                if(updateLdr==true){
                        helper.addSensor("ldr",dataSnapshot.getValue().toString());

                }else{
                    updateLdr =true;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        refTemp.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tsuhu.setText(dataSnapshot.getValue().toString()+" ºC");
                if(updateSuhu==true){
                        helper.addSensor("suhu",dataSnapshot.getValue().toString()+" ºC");

                }else{
                    updateSuhu =true;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
