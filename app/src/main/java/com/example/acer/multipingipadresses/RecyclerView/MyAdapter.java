package com.example.acer.multipingipadresses.RecyclerView;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.acer.multipingipadresses.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private final int idCurrentObject;
    List<Events> eventt = new ArrayList<>();


    public MyAdapter(List<Events> eventt, int idCurrentObject) {
        this.idCurrentObject = idCurrentObject;
        this.eventt = eventt;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.object_recycler_view, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public int getItemCount() {
        return eventt.size();
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Events event = eventt.get(position);
        holder.position = position;

        holder.txtDescript.setText(event.getDescrip());
        holder.txtHost.setText(event.getHost());
        holder.txtDevice.setText(event.getDevice());
        holder.txtAddress.setText(event.getAddress());
        holder.lastSpeed.setText((event.getImgSpeed3()) + "");
        holder.imgSpeed1.setBackgroundColor(Color.parseColor(collorImage(event.getImgSpeed1())));
        holder.imgSpeed2.setBackgroundColor(Color.parseColor(collorImage(event.getImgSpeed2())));
        holder.imgSpeed3.setBackgroundColor(Color.parseColor(collorImage(event.getImgSpeed3())));

        if(idCurrentObject == event.getObjectId()) {
            holder.txtDescript.setTextColor(Color.BLUE);
        } else {
            holder.txtDescript.setTextColor(Color.BLACK);
        }
    }

   class ViewHolder extends RecyclerView.ViewHolder {

       @Bind(R.id.Text_Descrip)
       TextView txtDescript;
       @Bind(R.id.Text_Host)
       TextView txtHost;
       @Bind(R.id.Text_Device)
       TextView txtDevice;
       @Bind(R.id.Text_Address)
       TextView txtAddress;
       @Bind(R.id.Last_Speed)
       TextView lastSpeed;
       @Bind(R.id.Img_View_Speed1)
       ImageView imgSpeed1;
       @Bind(R.id.Img_View_Speed2)
       ImageView imgSpeed2;
       @Bind(R.id.Img_View_Speed3)
       ImageView imgSpeed3;
       int position;

       public ViewHolder(View itemView) {
           super(itemView);
           ButterKnife.bind(this, itemView);

       }
   }

    public String collorImage(int speed) {
        String collor;
        if (speed < 30) {
            collor = "#157c00";
        } else if (speed < 150) {
            collor = "#fcb101";
        } else collor = "#f10900";

        return collor;
    }
}
