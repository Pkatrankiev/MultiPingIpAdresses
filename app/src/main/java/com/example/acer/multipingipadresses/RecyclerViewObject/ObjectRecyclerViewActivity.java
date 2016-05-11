package com.example.acer.multipingipadresses.RecyclerViewObject;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.acer.multipingipadresses.R;
import com.example.acer.multipingipadresses.database.DeviceAdapter;
import com.example.acer.multipingipadresses.database.HostAdapter;
import com.example.acer.multipingipadresses.database.ObjectAdapter;
import com.example.acer.multipingipadresses.database.models.Object;

import java.util.ArrayList;
import java.util.List;

public class ObjectRecyclerViewActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manag_object_recycler_view);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        List<ObjectEvents> eventsObjectList = generateEvents();


        RecyclerView recObjectView = (RecyclerView) findViewById(R.id.rec_view_object);
        recObjectView.setLayoutManager(new LinearLayoutManager(this));
        ObjectRecyclerAdapter objectRecyclerAdapter = new ObjectRecyclerAdapter(eventsObjectList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recObjectView.setLayoutManager(mLayoutManager);
        recObjectView.setItemAnimator(new DefaultItemAnimator());
        recObjectView.setAdapter(objectRecyclerAdapter);


        recObjectView.setAdapter(objectRecyclerAdapter);




    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        ObjectAdapter objectAdapter = new ObjectAdapter(this);
//        objectAdapter.delete(5);
    }

    private List<ObjectEvents> generateEvents() {


//        Log.e("-------------------", "88");
        List<ObjectEvents> eventsList = new ArrayList<>();
        DeviceAdapter deviceAdapter = new DeviceAdapter(this);
        HostAdapter hostAdapter = new HostAdapter(this);
        ObjectAdapter objectAdapter = new ObjectAdapter(this);


        List<Object> objectsList = new ArrayList<>();
        objectsList = objectAdapter.getAllRecords();


//        Log.e("-------------------", String.valueOf(objectsList.size()));
        for (Object object : objectsList) {

            ObjectEvents event = new ObjectEvents(
                    object.getId(),
                    object.getIpAddress(),
                    object.getDescrip(),
                    object.getAdress(),
                    object.getInfo(),
                    (hostAdapter.findById(object.getHostTypeId())).getHostType(),
                    (deviceAdapter.findById(object.getDeviceTypeId())).getDeviceType()
            );
            Log.e("***************",
                    "objectId= " + event.getObjectId() +
                            "Descri= " + event.getDescrip() +
                            " Host= " + event.getHostType() +
                            " Device= " + event.getDeviceType() +
                            " Adres= " + event.getAddress() +
                            " Info = " + event.getInfo() +
                            " Ip= " + event.getObjectIp()

            );
            eventsList.add(event);
        }

        return eventsList;


    }


}


