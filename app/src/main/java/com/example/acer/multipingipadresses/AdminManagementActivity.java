package com.example.acer.multipingipadresses;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.acer.multipingipadresses.RecyclerView.RecyclerViewActivity;
import com.example.acer.multipingipadresses.RecyclerViewObject.ObjectRecyclerViewActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AdminManagementActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.live_view_button)
    Button btnLiveView;
    @Bind(R.id.playback_button)
    Button btnPlayback;
    @Bind(R.id.object_management_button)
    Button btnObManag;
    @Bind(R.id.device_management_button)
    Button btnDevManag;
    @Bind(R.id.host_management_button)
    Button btnHostManag;
    @Bind(R.id.user_management_button)
    Button btnUserManag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_management);

        ButterKnife.bind(this);

        btnLiveView.setOnClickListener(this);
        btnPlayback.setOnClickListener(this);
        btnObManag.setOnClickListener(this);
        btnDevManag.setOnClickListener(this);
        btnHostManag.setOnClickListener(this);
        btnUserManag.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.live_view_button:
                if (isNetworkAvailable() == false) {
                    Toast.makeText(getApplicationContext(), "No Network Connection", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Влизате като live_view_button", Toast.LENGTH_LONG).show();
                    Intent previewIntent = new Intent(this, RecyclerViewActivity.class);
                    startActivity(previewIntent);
                }
                break;
            case R.id.playback_button:{
                Toast.makeText(getApplicationContext(), "Влизате като playback_button", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(this, RecyclerViewActivity.class);
                startActivity(intent);
                ;}
                break;
            case R.id.object_management_button:{
                Toast.makeText(getApplicationContext(), "Влизате като object_management_button", Toast.LENGTH_LONG).show();

                Intent ObjectIntent = new Intent(this, ObjectRecyclerViewActivity.class);
                startActivity(ObjectIntent);}
                break;
            case R.id.device_management_button:{
                Toast.makeText(getApplicationContext(), "Влизате като device_management_button", Toast.LENGTH_LONG).show();

                Intent DeviceIntent = new Intent(this, RecyclerViewActivity.class);
                startActivity(DeviceIntent);}
                break;
            case R.id.host_management_button:{
                Toast.makeText(getApplicationContext(), "Влизате като host_management_button", Toast.LENGTH_LONG).show();

                Intent HostIntent = new Intent(this, RecyclerViewActivity.class);
                startActivity(HostIntent);}
                break;
            case R.id.user_management_button:{
                Toast.makeText(getApplicationContext(), "Влизате като user_management_button", Toast.LENGTH_LONG).show();

                Intent UserIntent = new Intent(this, RecyclerViewActivity.class);
                startActivity(UserIntent);}
                break;
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void onBackPressed(){
        Intent a = new Intent(this,LoginActivity.class);

        startActivity(a);
    }
}
