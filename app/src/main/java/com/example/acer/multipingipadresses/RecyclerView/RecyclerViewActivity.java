package com.example.acer.multipingipadresses.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.acer.multipingipadresses.BarGraph.BarGraphActivity;
import com.example.acer.multipingipadresses.R;
import com.example.acer.multipingipadresses.database.DeviceAdapter;
import com.example.acer.multipingipadresses.database.HostAdapter;
import com.example.acer.multipingipadresses.database.ObjectAdapter;
import com.example.acer.multipingipadresses.database.SampleAdapter;
import com.example.acer.multipingipadresses.database.models.Object;
import com.example.acer.multipingipadresses.database.models.Sample;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        final List<Events> eventsList = generateEvents();


        final RecyclerView recView = (RecyclerView) findViewById(R.id.rec_view);
        recView.setLayoutManager(new GridLayoutManager(this, 3));
        final MyAdapter myAdapter = new MyAdapter(eventsList);

        recView.setAdapter(myAdapter);

        recView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Events events = eventsList.get(position);

                Toast.makeText(getApplicationContext(), events.getDescrip() +
                        " is selected!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(RecyclerViewActivity.this, BarGraphActivity.class);
                intent.putExtra("ObjectId", events.getObjectId());
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


    }
//    @Override
//    public void onBackPressed() {
//        // This will be called either automatically for you on 2.0
//        // or later, or by the code above on earlier versions of the
//        // platform.
//
//        return;
//    }

    private List<Events> generateEvents() {
//        Log.e("-------------------", "88");
        List<Events> events = new ArrayList<>();

        DeviceAdapter deviceAdapter = new DeviceAdapter(this);
        HostAdapter hostAdapter = new HostAdapter(this);
        ObjectAdapter objectAdapter = new ObjectAdapter(this);
        SampleAdapter sampleAdapter = new SampleAdapter(this);


        ArrayList<Object> objectsList = objectAdapter.getAllRecords();

        int imgSpeed1 = 0;
        int imgSpeed2 = 0;
        int imgSpeed3 = 0;
        Log.e("abc", " objectVal)= " + objectsList.size());

        for (Object object : objectsList) {

            ArrayList<Sample> sampleArrayList = sampleAdapter.findListByObjectId(object.getId());
            Log.e("abc", " sampleArrayList.size()= " + sampleArrayList.size() + "  ------sampleArrayList.size- " + String.valueOf(sampleArrayList.size()));

            for (int i = 0; i <= (sampleArrayList.size() - 3); i++) {
                imgSpeed1 = sampleArrayList.get(i).getMeasurementValue();
                imgSpeed2 = sampleArrayList.get(i + 1).getMeasurementValue();
                imgSpeed3 = sampleArrayList.get(i + 2).getMeasurementValue();
            }

            Events event = new Events(
                    object.getId(),
                    object.getDescrip(),
                    hostAdapter.findById(object.getHostTypeId()).getHostType(),
                    deviceAdapter.findById(object.getDeviceTypeId()).getDeviceType(),
                    object.getAdress(),
                    imgSpeed1,
                    imgSpeed2,
                    imgSpeed3
            );
            Log.e("***************",
                    "objectId= " + event.getObjectId() +
                            "Descri= " + event.getDescrip() +
                            " Host= " + event.getHost() +
                            " Device= " + event.getDevice() +
                            " Adres= " + event.getAddress() +
                            " Sped1= " + event.getImgSpeed1() +
                            " Sped2= " + event.getImgSpeed2() +
                            " Sped3= " + event.getImgSpeed3()

            );
            events.add(event);
        }

        return events;
    }


    private class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }

    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }
}