package com.example.acer.multipingipadresses.BarGraph;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.acer.multipingipadresses.R;
import com.example.acer.multipingipadresses.database.DeviceAdapter;
import com.example.acer.multipingipadresses.database.HostAdapter;
import com.example.acer.multipingipadresses.database.MeasurementAdapter;
import com.example.acer.multipingipadresses.database.ObjectAdapter;
import com.example.acer.multipingipadresses.database.SampleAdapter;
import com.example.acer.multipingipadresses.database.models.Object;
import com.example.acer.multipingipadresses.database.models.Sample;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class BarGraphActivity extends AppCompatActivity {

    @Bind(R.id.object_name)
    TextView txtObjectName;
    @Bind(R.id.object_ip)
    TextView txtObjecIp;
    @Bind(R.id.object_address)
    TextView txtObjectAddress;
    @Bind(R.id.object_info)
    TextView txtObjectInfo;
    @Bind(R.id.object_device)
    TextView txtObjectDevice;
    @Bind(R.id.object_host)
    TextView txtObjectHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_graph);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        ButterKnife.bind(this);

        BarChart barChart = (BarChart) findViewById(R.id.chart);
        int id = 0;
        int objectId = getIntent().getIntExtra("ObjectId", id);

        final EventGraph eventGraphList = generateEventGraph(objectId);

        txtObjectName.setText(eventGraphList.getDescrip());
        txtObjecIp.setText(eventGraphList.getObjectIp());
        txtObjectAddress.setText(eventGraphList.getAddress());
        txtObjectInfo.setText(eventGraphList.getInfo());
        txtObjectDevice.setText(eventGraphList.getDeviceType());
        txtObjectHost.setText(eventGraphList.getHostType());


        ArrayList<BarEntry> entries = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<String>();
        int i = 0;
        for (int speed : eventGraphList.getImgSpeed()) {

            entries.add(new BarEntry(speed, i));
            i++;
        }
        i = 0;
        for (String meas : eventGraphList.getTimeMeasurement()) {

            labels.add(meas);
            i++;
        }

        BarDataSet dataset = new BarDataSet(entries, "IP адрес:"+eventGraphList.getObjectIp());

        BarData data = new BarData(labels, dataset);
        // dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
        barChart.setData(data);
        barChart.animateY(2000);

    }


    private EventGraph generateEventGraph(int objectId) {
        Log.e("2222222222222222", "22");

        DeviceAdapter deviceAdapter = new DeviceAdapter(this);
        HostAdapter hostAdapter = new HostAdapter(this);
        MeasurementAdapter measAdapter = new MeasurementAdapter(this);
        ObjectAdapter objectAdapter = new ObjectAdapter(this);
        SampleAdapter sampleAdapter = new SampleAdapter(this);

        Log.e("abc", " objectId= " + objectId);
        Object object = objectAdapter.findById(objectId);
        List<Sample> sampleList = new ArrayList<>();
        Log.e("abc", " object.getId()= " + object.getId());
        sampleList = sampleAdapter.findListByObjectId(object.getId());
        Log.e("abc", "  ------sampleArrayList.size- " + (sampleList.size()));
        ArrayList<Integer> imgSpeed = new ArrayList<>();
        ;
        ArrayList<String> timeMeasurement = new ArrayList<>();
        ;
        int sizeSampleArrayList = sampleList.size();
        int validMeasurement =0;
        if (sizeSampleArrayList < 30) {
            for (int i = 0; i < sampleList.size(); i++) {
                if (sampleList.get(i).getMeasurementValue()>200) {
                    validMeasurement = 200;

                }else{
                    validMeasurement = sampleList.get(i).getMeasurementValue();
                }
                imgSpeed.add(validMeasurement);
                timeMeasurement.add(sampleList.get(i).getTimeMeasurement());
            }
        } else {

            Log.e("abc", "  ------sampleArrayList.size- " + (sampleList.size()));
            for (int i = sizeSampleArrayList-30; i <sizeSampleArrayList; i++) {
                if (sampleList.get(i).getMeasurementValue()>200) {
                    validMeasurement = 200;

                }else{
                    validMeasurement = sampleList.get(i).getMeasurementValue();
                }
                imgSpeed.add(validMeasurement);
                timeMeasurement.add(sampleList.get(i).getTimeMeasurement());
                Log.e("abc", "  imgSpeed[" + i + "]= " + sampleList.get(i).getMeasurementValue());
            }
        }
        Log.e("abc", " imgSpeed.length= " + imgSpeed.size());
        EventGraph eventGraph = new EventGraph(
                object.getIpAddress(),
                object.getDescrip(),
                object.getAdress(),
                object.getInfo(),
                (hostAdapter.findById(object.getHostTypeId())).getHostType(),
                (deviceAdapter.findById(object.getDeviceTypeId())).getDeviceType(),
                imgSpeed,
                timeMeasurement
        );

        for (int s : imgSpeed) {
            Log.e("abc", "  Speed[" + s + "]= " + s);
        }


        Log.e("abc", " event.getImgSpeed().length= " + eventGraph.getImgSpeed().size());
        Log.e("***************",
                " objectIp= " + eventGraph.getObjectIp() +
                        " Descri= " + eventGraph.getDescrip() +
                        " Info= " + eventGraph.getInfo() +
                        " Host= " + eventGraph.getHostType() +
                        " Device= " + eventGraph.getDeviceType() +
                        " Adres= " + eventGraph.getAddress() +
                        " br.Measurement= " + eventGraph.getImgSpeed().size()
        );


        return eventGraph;
    }

}