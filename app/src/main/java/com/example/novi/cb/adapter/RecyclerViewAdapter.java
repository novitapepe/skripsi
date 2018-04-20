package com.example.novi.cb.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.novi.cb.R;
import com.example.novi.cb.model.DataDetails;
import com.example.novi.cb.sqlite.SqliteCRUDHelper;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    Activity context;
    List<String> sensorVal;

    int sensortype = 0;


    public RecyclerViewAdapter(Activity context, int sensortype, List<String> sensorVal) {
        this.context = context;
        this.sensortype = sensortype;
        this.sensorVal = sensorVal;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lvdesain,null);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if(sensortype==0){
            holder.txtnama.setText("Sensor Cahaya");
            holder.txtstatus.setText(sensorVal.get(position));

            if (sensorVal.get(position).equalsIgnoreCase(" on")) {
                holder.imgsensor.setImageResource(R.drawable.lampu_on1);
            } else {
                holder.imgsensor.setImageResource(R.drawable.lampu_off);
            }

        }else if (sensortype==1){
            holder.txtnama.setText("Sensor Pintu");
            holder.txtstatus.setText(sensorVal.get(position));

            if (sensorVal.get(position).equalsIgnoreCase("open")) {
                holder.imgsensor.setImageResource(R.drawable.door1);
            } else {
                holder.imgsensor.setImageResource(R.drawable.door0);
            }
        }else if (sensortype==2){
            holder.txtnama.setText("Sensor Temperatur");
            holder.txtstatus.setText(sensorVal.get(position) + " °C");

            double suhu = Double.parseDouble(sensorVal.get(position));

            if (suhu > 29.00) {
                holder.imgsensor.setImageResource(R.drawable.suhu0);
            } else {
                holder.imgsensor.setImageResource(R.drawable.suhu1);
            }
        }



    }

    @Override
    public int getItemCount() {
        return sensorVal.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtnama, txtstatus, txttime;
        ImageView imgsensor;
        public ViewHolder(View v) {
            super(v);
            imgsensor = (ImageView) v.findViewById(R.id.imgsensor);
            txtnama = (TextView) v.findViewById(R.id.txtnamasensor);
            txtstatus = (TextView) v.findViewById(R.id.txtstatussensor);
//            txttime = (TextView) v.findViewById(R.id.txttime);
        }
    }
}
