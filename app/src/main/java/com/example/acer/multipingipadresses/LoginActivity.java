package com.example.acer.multipingipadresses;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.acer.multipingipadresses.database.DataBaseActivity;
import com.example.acer.multipingipadresses.database.UserAdapter;
import com.example.acer.multipingipadresses.database.models.User;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.user_name)
    EditText editTextUserName;
    @Bind(R.id.user_pass)
    EditText editTextUserPass;
    @Bind(R.id.sendButton)
    Button btnUserSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(LoginActivity.this);
        editTextUserName.setText("Admin");



        btnUserSend.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {


                                               if (isNetworkAvailable() == false) {
                                                   Toast.makeText(getApplicationContext(), "No Network Connection", Toast.LENGTH_LONG).show();
                                               } else {
                                                   if (checkUser().equals("not")) {
                                                       Toast.makeText(getApplicationContext(), "Invalid name or password", Toast.LENGTH_LONG).show();

                                                   } else {
                                                       Intent intentDB = new Intent(LoginActivity.this, DataBaseActivity.class);
                                                       intentDB.putExtra("User_Type",checkUser());
                                                       startActivity(intentDB);

                                                   }
                                               }

                                           }
                                       }
        );
    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private String checkUser() {
        UserAdapter user = new UserAdapter(this);
        if (user.getAllRecords().size() == 0) {
            user.insert(new User("Admin", "123", "admin"));
            user.insert(new User("User", "123", "user"));
        }
        String userName = String.valueOf(editTextUserName.getText());
        String userPass = String.valueOf(editTextUserPass.getText());

        if (checkData(userName, userPass)) {
            ArrayList<User> userList = user.getAllRecords();
            for (User curentUser : userList) {
                if (userName.equals(curentUser.getUserName()) &&
                        userPass.equals(curentUser.getUserPass())) {
                    String userType = curentUser.getUserType();
                    return userType;
                }
            }
        }
        return "not";
    }

    private boolean checkData(String userName, String userPass) {

        String emailPattern = "^.*[^a-zA-Z0-9._-].*$";
        if (userName.length() == 0) {
            editTextUserName.setError("Въведете име!");
            return false;
        }
        if (userPass.length() == 0) {
            editTextUserPass.setError("Въведете парола!");
            return false;
        }
        if (userName.matches(emailPattern)) {
            editTextUserName.setError("Не позволени символи в името!");
            return false;
        }
        if (userPass.matches(emailPattern)) {

            editTextUserPass.setError("Не позволени символи в паролата!");
            return false;
        }
        return true;
    }

}
