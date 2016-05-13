package com.example.acer.multipingipadresses.RecyclerViewObject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

import butterknife.Bind;
import butterknife.ButterKnife;


public class ObjectRecyclerAdapter extends RecyclerView.Adapter<ObjectRecyclerAdapter.ViewHolder> {

    List<ObjectEvents> eventt = new ArrayList<>();
    public Context context;

    private View dialog;
    private Fragment fragment;
    private FragmentTransaction ft;

    Spinner spinnerDevice;
    Spinner spinnerHost;

    ArrayAdapter<String> SpinnerAdapter;


    public ObjectRecyclerAdapter(List<ObjectEvents> eventt) {
        this.eventt = eventt;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.object_manag_recycler_view, parent, false);
        ViewHolder vh = new ViewHolder(v);

        return vh;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ObjectEvents event = eventt.get(position);
        holder.position = position;
        holder.layoutObject.setBackgroundColor(Color.parseColor(collorImage(position)));
        holder.txtDescript.setText(event.getDescrip());
        holder.txtIp.setText(event.getObjectIp());
        holder.txtAddress.setText(event.getAddress());
        holder.txtInfo.setText(event.getInfo());
        holder.txtHostType.setText(event.getHostType());
        holder.txtDeviceType.setText(event.getDeviceType());

        context = holder.itemView.getContext();

    }

    public void addObject(ObjectEvents events) {
        eventt.add(0, events);
        notifyItemInserted(0);
    }

    private String collorImage(int position) {
        if (position % 2 == 0) {
            return "#FFC6C6C6";
        }
        return "#FFFFFFFF";
    }


    @Override
    public int getItemCount() {
        return eventt.size();
    }

    public LayoutInflater getLayoutInflater() {
        LayoutInflater layoutInflater = null;
        return layoutInflater;


    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.layout_object)
        LinearLayout layoutObject;

        @Bind(R.id.text_descrip)
        TextView txtDescript;
        @Bind(R.id.text_ip)
        TextView txtIp;
        @Bind(R.id.text_address)
        TextView txtAddress;
        @Bind(R.id.text_info)
        TextView txtInfo;
        @Bind(R.id.text_host_type)
        TextView txtHostType;
        @Bind(R.id.text_device_type)
        TextView txtDeviceType;


        @Bind(R.id.image_button_edit)
        ImageView imgBtnEdit;
        @Bind(R.id.image_button_del)
        ImageView imgBtnDel;


        int position;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


            imgBtnDel.setOnClickListener(this);
            imgBtnEdit.setOnClickListener(this);
        }

        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.image_button_del: {
                    String dialogMessage = "Изтриване от списъка на обект: " + eventt.get(position).getDescrip();
                    String dialogTitle = "Сигурни ли сте, че искате да изтриете обекта?";

                    AlertDialog.Builder builder = new AlertDialog.Builder(context)
                            .setIcon(R.drawable.delete_icon)
                            .setMessage(dialogMessage)
                            .setTitle(dialogTitle)
                            .setPositiveButton("Да", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int id) {

                                    ObjectAdapter objectAdapter = new ObjectAdapter(context);
                                    objectAdapter.delete(eventt.get(position).getObjectId());

                                    eventt.remove(position);
                                    notifyItemRemoved(position);
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            notifyDataSetChanged();
                                        }
                                    }, 500);

                                }
                            })
                            .setNegativeButton("Не", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                }
                            });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }

                break;
                case R.id.image_button_edit: {


                    View dialogFragment = LayoutInflater.from(context).inflate(R.layout.dialog_fragment, null);

                    final EditText editTxtDescript = (EditText) dialogFragment.findViewById(R.id.edit_txt_descrip);
                    final EditText editTxtIpAddress = (EditText) dialogFragment.findViewById(R.id.edit_txt_ip);
                    final EditText editTxtAddress = (EditText) dialogFragment.findViewById(R.id.edit_txt_address);
                    final EditText editTxtInfo = (EditText) dialogFragment.findViewById(R.id.edit_txt_info);


                    Host host = new Host();
                    final DeviceAdapter devAdap = new DeviceAdapter(context);

//                    ////////////////////////////////////////////////////


                    spinnerHost = (Spinner) dialogFragment.findViewById(R.id.spinner_host_type);


//                    1111111111111111111111111111111111111111111111111111111111111111

                    ArrayList<String> hostStringList = new ArrayList<>();
                    final HostAdapter hostAdap = new HostAdapter(context);
                    List<Host> listHost = new ArrayList<Host>(); // List of Items
                    listHost = hostAdap.getAllRecords();
                    int kk=0;
                    Log.e("curentHost", " size lis= "+listHost.size());
                    for (Host curentHost : listHost) {
                            String str = curentHost.getHostType();
                        Log.e("curentHost", " curentHost = " + str+" kk = "+kk);
                        hostStringList.add(str);
                        kk++;
                    }
                    SpinnerAdapter = new ArrayAdapter<String>
                            (context, android.R.layout.simple_spinner_item, hostStringList) {

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
                    spinnerHost.setAdapter(SpinnerAdapter);
                    // Set Adapter in the spinner

                    spinnerHost.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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


//      2222222222222222222222222222222222222222222222222222222222222222222222
                    ArrayList<String> deviceList = new ArrayList<>();
                    spinnerDevice = (Spinner) dialogFragment.findViewById(R.id.spinner_device_type);
                    List<Device> listDev = new ArrayList<Device>(); // List of Items
                    listDev = devAdap.getAllRecords();
                    for (Device curentDev : listDev) {
                        deviceList.add(curentDev.getDeviceType());
                    }


//
                    SpinnerAdapter = new ArrayAdapter<String>
                            (context, android.R.layout.simple_spinner_item, deviceList) {

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



                    spinnerDevice.setAdapter(SpinnerAdapter);
                    spinnerDevice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

//      ////////////////////////////////////////////////////////////


                    ObjectAdapter editObjectAdap = new ObjectAdapter(context);

                    final int objectId = eventt.get(position).getObjectId();
                    Object editObject = editObjectAdap.findById(objectId);

                    editTxtDescript.setText(editObject.getDescrip());
                    editTxtIpAddress.setText(editObject.getIpAddress());
                    editTxtAddress.setText(editObject.getAdress());
                    editTxtInfo.setText(editObject.getInfo());

                    selectValue(spinnerHost,(hostAdap.findById(editObject.getHostTypeId())).getHostType());
                    selectValue(spinnerDevice,(devAdap.findById(editObject.getDeviceTypeId())).getDeviceType());

                    final int hostID = editObject.getHostTypeId();
                    final int deviceID = editObject.getDeviceTypeId();

                    String descr = String.valueOf(editTxtAddress.getText());


                    String dialogTitle = "Променете данните на обект: " + eventt.get(position).getDescrip();
                    String dialogMessage = "Запазване промените на обекта?";

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setIcon(R.drawable.edit_icon);
                    builder.setTitle(dialogTitle);
                    builder.setView(dialogFragment);
                    builder.setMessage(dialogMessage);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            notifyDataSetChanged();
                        }
                    }, 500);
                    builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {


                        public void onClick(DialogInterface dialog, int id) {
                            ObjectAdapter objectAdapter = new ObjectAdapter(context);


//
                            String descr = String.valueOf(editTxtDescript.getText());
                            String ipAddress = String.valueOf(editTxtIpAddress.getText());
                            String address = String.valueOf(editTxtAddress.getText());
                            String info = String.valueOf(editTxtInfo.getText());


//                            String hostType = String.valueOf(editTxtHostType.getText());
//                            String deviceType = String.valueOf(editTxtDeviceType.getText());

                            int hostId = hostAdap.findByHosType(spinnerHost.getSelectedItem().toString()).getId();
                           int deviceId = devAdap.findByDevType(spinnerDevice.getSelectedItem().toString()).getId();

                            Object obj = new Object(ipAddress, descr, address, info, hostID, deviceId);
                            objectAdapter.update_byID(objectId, obj);

                            Log.e("-------------------", String.valueOf(editTxtAddress.getText()));
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    notifyDataSetChanged();
                                }
                            }, 500);



                        }
                    });
                    builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });


                    int width = context.getResources().getDisplayMetrics().widthPixels;
                    int height = context.getResources().getDisplayMetrics().heightPixels;
                    dialogFragment.setLayoutParams(new LinearLayout.LayoutParams(width, height));

                    AlertDialog dialog = builder.create();
                    dialog.show();
                    break;

                }

            }

        }

        public void updateData(List<ObjectEvents> viewModels) {
            eventt.clear();
            eventt.addAll(viewModels);
            notifyDataSetChanged();
        }
//                -----------dialog fragment--------------


        public View getDialog() {
            return dialog;


        }

        private void selectValue(Spinner spinner, String value) {
            for (int i = 0; i < spinner.getCount(); i++) {
                if (spinner.getItemAtPosition(i).equals(value)) {
                    spinner.setSelection(i);
                    break;
                }
            }
        }


    }
}


