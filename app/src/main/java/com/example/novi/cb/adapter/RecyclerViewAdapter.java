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

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    SqliteCRUDHelper helper ;
    Activity context;
    List<DataDetails> datasensor;

    int sensortype = 0;


    public RecyclerViewAdapter(Activity context, int sensortype) {
        this.context = context;
        this.sensortype = sensortype;

        helper=new SqliteCRUDHelper(context);
        datasensor = helper.getDetailSensor();
        Toast.makeText(context, "" + datasensor.size(), Toast.LENGTH_SHORT).show();
        Toast.makeText(context, "" + datasensor.get(0).getSensor(), Toast.LENGTH_SHORT).show();

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
            holder.txtstatus.setText(datasensor.get(position).getValue());
            holder.txttime.setText(datasensor.get(position).getTime());

        }else if (sensortype==1){
            holder.txtnama.setText("Sensor Pintu");
            holder.txtstatus.setText(datasensor.get(position).getValue());
            holder.txttime.setText(datasensor.get(position).getTime());
        }else if (sensortype==2){
            holder.txtnama.setText("Sensor Temperatur");
            holder.txtstatus.setText(datasensor.get(position).getValue());
            holder.txttime.setText(datasensor.get(position).getTime());
        }



    }

    @Override
    public int getItemCount() {
        return datasensor.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtnama, txtstatus, txttime;
        ImageView imgsensor;
        public ViewHolder(View v) {
            super(v);
            imgsensor = (ImageView) v.findViewById(R.id.imgsensor);
            txtnama = (TextView) v.findViewById(R.id.txtnamasensor);
            txtstatus = (TextView) v.findViewById(R.id.txtstatussensor);
            txttime = (TextView) v.findViewById(R.id.txttime);
        }
    }
}
