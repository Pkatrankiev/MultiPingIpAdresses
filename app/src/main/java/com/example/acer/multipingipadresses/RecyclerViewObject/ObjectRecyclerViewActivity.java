package com.example.acer.multipingipadresses.RecyclerViewObject;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.acer.multipingipadresses.R;
import com.example.acer.multipingipadresses.database.DeviceAdapter;
import com.example.acer.multipingipadresses.database.HostAdapter;
import com.example.acer.multipingipadresses.database.ObjectAdapter;
import com.example.acer.multipingipadresses.database.models.Device;
import com.example.acer.multipingipadresses.database.models.Host;
import com.example.acer.multipingipadresses.database.models.Object;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ObjectRecyclerViewActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.edit_add_descrip)
    EditText editAddDescrip;
    @Bind(R.id.edit_add_ip)
    EditText editAddIpAddress;
    @Bind(R.id.edit_add_address)
    EditText editAddAddress;
    @Bind(R.id.edit_add_info)
    EditText editAddInfo;
    @Bind(R.id.spinner_add_host_type)
    Spinner editAddSpinnerHostType;
    @Bind(R.id.image_button_add)
    ImageView editAddImageButtonAdd;

    @Bind(R.id.spinner_add_device_type)
    Spinner editAddSpinnerDeviceType;
    ArrayAdapter<String> SpinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manag_object_recycler_view);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        List<ObjectEvents> eventsObjectList = generateEvents();
        ButterKnife.bind(this);

        editAddImageButtonAdd.setOnClickListener(this);

        RecyclerView recObjectView = (RecyclerView) findViewById(R.id.rec_view_object);
        recObjectView.setLayoutManager(new LinearLayoutManager(this));
        ObjectRecyclerAdapter objectRecyclerAdapter = new ObjectRecyclerAdapter(eventsObjectList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recObjectView.setLayoutManager(mLayoutManager);
        recObjectView.setItemAnimator(new DefaultItemAnimator());
        recObjectView.setAdapter(objectRecyclerAdapter);
        recObjectView.setAdapter(objectRecyclerAdapter);

        // spiner

//                 host


        ArrayList<String> hostStringList = new ArrayList<>();
        final HostAdapter hostAdap = new HostAdapter(this);
        List<Host> listHost = new ArrayList<Host>(); // List of Items
        listHost = hostAdap.getAllRecords();
        int kk = 0;
        Log.e("curentHost", " size lis= " + listHost.size());
        for (Host curentHost : listHost) {
            String str = curentHost.getHostType();
            Log.e("curentHost", " curentHost = " + str + " kk = " + kk);
            hostStringList.add(str);
            kk++;
        }
        SpinnerAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, hostStringList) {

            public View getView(int position, View convertView,
                                ViewGroup parent) {
                View v = super.getView(position, convertView, parent);


                ((TextView) v).setTextColor(Color.parseColor("#E30D81"));
                return v;
            }

            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View v = super.getDropDownView(position, convertView,
                        parent);
                v.setBackgroundColor(Color.parseColor("#E30D81"));

                ((TextView) v).setTextColor(Color.parseColor("#ffffff"));

                return v;
            }
        };
        SpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editAddSpinnerHostType.setAdapter(SpinnerAdapter);
        // Set Adapter in the spinner

        editAddSpinnerHostType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                String stateHost = parentView.getItemAtPosition(position).toString(); // selected item in the list
//                            ((TextView) findViewById(R.id.selectedText)).setText(state);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });


//       device

        final DeviceAdapter devAdap = new DeviceAdapter(this);
        ArrayList<String> deviceList = new ArrayList<>();
        List<Device> listDev = new ArrayList<Device>(); // List of Items
        listDev = devAdap.getAllRecords();
        for (Device curentDev : listDev) {
            deviceList.add(curentDev.getDeviceType());
        }


//
        SpinnerAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, deviceList) {

            public View getView(int position, View convertView,
                                ViewGroup parent) {
                View v = super.getView(position, convertView, parent);


                ((TextView) v).setTextColor(Color.parseColor("#E30D81"));
                return v;
            }

            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View v = super.getDropDownView(position, convertView,
                        parent);
                v.setBackgroundColor(Color.parseColor("#E30D81"));

                ((TextView) v).setTextColor(Color.parseColor("#ffffff"));

                return v;
            }
        };


        editAddSpinnerDeviceType.setAdapter(SpinnerAdapter);
        editAddSpinnerDeviceType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                String stateDevice = parentView.getItemAtPosition(position).toString(); // selected item in the list
//                            ((TextView) findViewById(R.id.selectedText)).setText(state);
            }


            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });


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


    @Override
    public void onClick(View v) {


        ObjectAdapter objectAdapter = new ObjectAdapter(this);
        HostAdapter hostAdap = new HostAdapter(this);
        DeviceAdapter devAdap = new DeviceAdapter(this);


        String descr = String.valueOf(editAddDescrip.getText());
        String ipAddress = String.valueOf(editAddIpAddress.getText());
        String address = String.valueOf(editAddAddress.getText());
        String info = String.valueOf(editAddInfo.getText());

        if (checkData(descr, editAddDescrip) &&
                checkData(address, editAddAddress) &&
                checkData(info, editAddInfo)&&
                validateIp(ipAddress,editAddIpAddress ))
         {

            int hostId = hostAdap.findByHosType(editAddSpinnerHostType.getSelectedItem().toString()).getId();
            int deviceId = devAdap.findByDevType(editAddSpinnerDeviceType.getSelectedItem().toString()).getId();

            Object obj = new Object(ipAddress, descr, address, info, hostId, deviceId);

            objectAdapter.insert(obj);
            reload();
            Log.e("-------------------", String.valueOf(editAddAddress.getText()));
        }

    }


    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    private boolean checkData(String str, EditText editText) {

        String pattern = "^.*[^a-zA-Z0-9._-].*$";
        if (str.length() < 3) {
            editText.setError("Твърде малко символи!");
            return false;
        }

        if (str.matches(pattern)) {
            editText.setError("Не позволени символи!");
            return false;
        }

        return true;
    }


    public boolean validateIp(String ip, EditText editText) {
        String IPADDRESS_PATTERN =
                "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

        Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);
        Matcher matcher = pattern.matcher(ip);

        if (!matcher.matches()) {
            editText.setError("Не коректен IP адрес!");
            return false;
        }

        return matcher.matches();
    }
}




