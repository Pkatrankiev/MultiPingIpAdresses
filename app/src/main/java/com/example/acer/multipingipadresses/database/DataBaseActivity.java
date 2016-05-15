package com.example.acer.multipingipadresses.database;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.acer.multipingipadresses.AdminManagementActivity;
import com.example.acer.multipingipadresses.database.models.Device;
import com.example.acer.multipingipadresses.database.models.Host;
import com.example.acer.multipingipadresses.database.models.Measurement;
import com.example.acer.multipingipadresses.database.models.Object;
import com.example.acer.multipingipadresses.database.models.Sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataBaseActivity extends ActionBarActivity implements TaskCompleted {
    ProgressDialog pDialog;

//    public ArrayList<Object> objectList;
//    public ArrayList<ObjectValues> objectValuesList;
//    public ArrayList<ObjectString> valuesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_data_base);

        CheckDataBase();

        Intent intent1 = getIntent();
        Bundle bundle = intent1.getExtras();
        String data = "";
        if (bundle != null) {
            data = bundle.getString("User_Type");
        }

        if (data.equals("admin")) {
            Toast.makeText(getApplicationContext(), "Влизате като админ", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, AdminManagementActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Влизате като юзер", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, AdminManagementActivity.class);

            startActivity(intent);
        }

    }

    public void CheckDataBase() {

        boolean fl = false;

        ObjectAdapter objectAdapter = new ObjectAdapter(this);

        Log.e("DBActivity-onCreate", "objectAdapter size = " + objectAdapter.getAllRecords().size());

        if (objectAdapter.getAllRecords().size() == 0) {
            new addDefaultDataAsyncTask(this).execute();
            fl = true;
        }

        new PingDataAsyncTask().execute();


    }

    public static String getCurrentTimeStamp() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTimeStamp = dateFormat.format(new Date()); // Find todays date

            return currentTimeStamp;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public int Random(int min, int max) {
        Random r = new Random();
        int rndm = r.nextInt(max - min + 1) + min;
        return rndm;
    }

    @Override
    public void onTaskComplete(Integer result) {

        Toast.makeText(this, "The result is " + Integer.toString(result), Toast.LENGTH_LONG).show();
    }

//**********************************************************************

    public class addDefaultDataAsyncTask extends AsyncTask<Void, String, Integer> {
        private Context mContext;


        ProgressDialog mProgress;
        private TaskCompleted mCallback;

        public addDefaultDataAsyncTask(Context context) {
            this.mContext = context;
            this.mCallback = (TaskCompleted) context;

        }

        @Override
        public void onPreExecute() {

            super.onPreExecute();
            mProgress = new ProgressDialog(DataBaseActivity.this);
            mProgress.setMessage("Изчакайте, зареждам първоначални данни...");
            mProgress.show();
        }

        @Override
        protected void onProgressUpdate(String... values) {
            mProgress.setMessage(values[0]);
        }

        @Override
        protected Integer doInBackground(Void... values) {


//            +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            Integer iResult = 0;

            //This is to simulate the calculation is super hard to calculate
            //and we make it slow down by 500 ms for each number


            DeviceAdapter devAdapter = new DeviceAdapter(DataBaseActivity.this);
            HostAdapter hostAdapter = new HostAdapter(DataBaseActivity.this);
            MeasurementAdapter measAdapter = new MeasurementAdapter(DataBaseActivity.this);
            ObjectAdapter objectAdapter = new ObjectAdapter(DataBaseActivity.this);
            SampleAdapter sampleAdapter = new SampleAdapter(DataBaseActivity.this);

            devAdapter.insert(new Device("Camera"));
            devAdapter.insert(new Device("DVR"));
            devAdapter.insert(new Device("NVR"));
            devAdapter.insert(new Device("GPRS"));

            hostAdapter.insert(new Host("Apartment"));
            hostAdapter.insert(new Host("House"));
            hostAdapter.insert(new Host("Shop"));
            hostAdapter.insert(new Host("Store"));

            measAdapter.insert(new Measurement("Ping_Speed"));

            objectAdapter.insert(new Object("213.231.171.91", "Office SITY", "Vratza, ul.Lukashov8", "Vhod Trezor", 1, 1));
            objectAdapter.insert(new Object("213.231.176.206", "Golqmo materialno", "Vratza, bul.M.Orozov 55", "teren", 4, 3));
            objectAdapter.insert(new Object("213.231.176.207", "Malko materialno", "Vratza, bul.M.Orozov 34", "baza", 4, 3));
            objectAdapter.insert(new Object("213.231.164.204", "Ekokeramika", "Vratza, bul.Hr.Botev 55", "teren", 3, 3));
            objectAdapter.insert(new Object("85.187.236.23", "Turkanica", "Vratza, Turkanica", "Kusta i dwor", 1, 1));
            objectAdapter.insert(new Object("213.231.167.82", "Zlatarski m-n", "Vratza, bul.N.Voivodov 6", "zlatarski m-n", 4, 3));
            objectAdapter.insert(new Object("151.237.73.86", "Avto serviz Bobi", "Vratza, krivodolsko shose", "baza", 4, 3));
            objectAdapter.insert(new Object("151.237.78.53", "Vratchanski balkan", "Vratza, patq za Pavloche", "teren", 3, 3));
            objectAdapter.insert(new Object("77.76.129.66", "kasta Buzov", "Vratza, pazara", "Kusta i dwor", 1, 1));
            objectAdapter.insert(new Object("213.231.145.127", "Dvor maistor Mito", "Vratza, Hr.Botev44 6", "kusta i dvor", 4, 3));
            objectAdapter.insert(new Object("83.228.26.205", "kafe VIP", "Vratza, Postata", "kafene", 4, 3));
            objectAdapter.insert(new Object("151.237.78.129", "u-ste p.Beron", "Vratza, Metkovec", "Uchiliste", 3, 3));
            objectAdapter.insert(new Object("213.231.171.91", "Office SITY", "Vratza, ul.Lukashov8", "Vhod Trezor", 1, 1));
            objectAdapter.insert(new Object("213.231.176.206", "Golqmo materialno", "Vratza, bul.M.Orozov 55", "teren", 4, 3));
            objectAdapter.insert(new Object("213.231.176.207", "Malko materialno", "Vratza, bul.M.Orozov 34", "baza", 4, 3));
            objectAdapter.insert(new Object("213.231.164.204", "Ekokeramika", "Vratza, bul.Hr.Botev 55", "teren", 3, 3));
            objectAdapter.insert(new Object("85.187.236.23", "Turkanica", "Vratza, Turkanica", "Kusta i dwor", 1, 1));
            objectAdapter.insert(new Object("213.231.167.82", "Zlatarski m-n", "Vratza, bul.N.Voivodov 6", "zlatarski m-n", 4, 3));
            objectAdapter.insert(new Object("151.237.73.86", "Avto serviz Bobi", "Vratza, krivodolsko shose", "baza", 4, 3));
            objectAdapter.insert(new Object("151.237.78.53", "Vratchanski balkan", "Vratza, patq za Pavloche", "teren", 3, 3));
            objectAdapter.insert(new Object("77.76.129.66", "kasta Buzov", "Vratza, pazara", "Kusta i dwor", 1, 1));
            objectAdapter.insert(new Object("213.231.145.127", "Dvor maistor Mito", "Vratza, Hr.Botev44 6", "kusta i dvor", 4, 3));
            objectAdapter.insert(new Object("83.228.26.205", "kafe VIP", "Vratza, Postata", "kafene", 4, 3));
            objectAdapter.insert(new Object("151.237.78.129", "u-ste p.Beron", "Vratza, Metkovec", "Uchiliste", 3, 3));

            int count = 1;
            int allCount = 1;


            Integer iLength = 4 * objectAdapter.getAllRecords().size();
            for (int j = 1; j <= 4; j++) {
                for (int i = 1; i <= objectAdapter.getAllRecords().size(); i++) {
                    sampleAdapter.insert(new Sample(i, 1, Random(5, 200), getCurrentTimeStamp()));
                    allCount++;
                    try {
                        Thread.sleep(Random(1000, 3000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Sample sp = new Sample();
                    sp = sampleAdapter.findById(count);
                    publishProgress("Task Completed :" + Integer.toString(count) + " of " + Integer.toString(iLength));


                    Log.e("addDefaltDataBase", "Sample: i= " + count + " Object= " + objectAdapter.findById(sp.getObjectId()).getDescrip() + " meas= " + sp.getMeasurementValue() + " time= " + sp.getTimeMeasurement());
                    count++;
                    try {
                        Thread.sleep(allCount);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }


//                ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            return count;
        }


        @Override
        protected void onPostExecute(Integer results) {
            mProgress.dismiss();
            //This is where you return data back to caller
            mCallback.onTaskComplete(results);
        }
    }


//    *****************************************************************

    public class PingDataAsyncTask extends AsyncTask<Void, Void, Void> {
        private Context mContext;

        @Override
        protected void onPreExecute() {
            // вызывается в потоке пользовательского интерфейса, прежде чем задача будет выполнена
            // обновляем пользовательский интерфейс сразу после выполнения задачи
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {


            ObjectAdapter objectAdapter = new ObjectAdapter(DataBaseActivity.this);
            SampleAdapter sampleAdapter = new SampleAdapter(DataBaseActivity.this);

            Log.e("addPingData", "Broy Object = " + objectAdapter.getAllRecords().size());
//            int k = 0;
//            while (k < 2) {
            List<Object> objectList = new ArrayList<>();
            objectList = objectAdapter.getAllRecords();

//            for (int i = 1; i <= objectList.size(); i++) {
//                Log.e("addPingData", "ping i = " + i);
//                int idCurendObject = objectAdapter.findById(i).getId();
//                Log.e("addPingData", "ping idCurendObject = " + idCurendObject);
//                int speed = 0;
//                String url = objectAdapter.findById(i).getIpAddress();
//
//                speed = ping(url);
//                Log.e("addPingData", " ping url = " + url);
//
//
//                sampleAdapter.insert(new Sample(idCurendObject,
//                        1,
//                        speed,
//                        getCurrentTimeStamp()));
//
//                Log.e("addPingData", "Object ID= " + idCurendObject +
//                        " Object IP= " + objectAdapter.findById(i).getIpAddress() +
//                        " PingSpeed = " + speed +
//                        " time = " + getCurrentTimeStamp());
//
//            }
            int k = 0;
            while (k < 2) {
                for (Object curenrObject : objectList) {
                    int idCurendObject = curenrObject.getId();
                    Log.e("addPingData", "ping idCurendObject = " + idCurendObject + "  ping k = " + k);
                    int speed = 0;
                    String url = curenrObject.getIpAddress();

                    speed = ping(url);
                    Log.e("addPingData", " ping url = " + url);


                    sampleAdapter.insert(new Sample(idCurendObject,
                            1,
                            speed,
                            getCurrentTimeStamp()));

                    Log.e("addPingData", "Object ID= " + idCurendObject +
                            " Object IP= " + curenrObject.getIpAddress() +
                            " PingSpeed = " + speed +
                            " time = " + getCurrentTimeStamp());

                }

            }
//            }


//            //вызывается в фоновом потоке сразу после onPreExecute ()
//            //выполнения вычислений в фоновом режиме
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

            //вызывается в потоке пользовательского интерфейса после вызова publishProgress(Progress…)
            //метод используется для отображения любых форм прогресса

        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            // вызывается в потоке пользовательского интерфейса после выполнения процесса
            // вычислений в фоновом режиме. Результат вычислений передается на этот шаг в качестве параметра


        }


    }


    public int ping(String url) {
        int speed = 0;
        int str = 0;
//        -----------------------------------------------------------------

        float sum = (float) 0.3;
//        url = "213.231.171.91";
        try {

            Process process = null;

            if (Build.VERSION.SDK_INT <= 16) {
                // shiny APIS

                process = Runtime.getRuntime().exec(
                        "/system/bin/ping -w 1 -c 1 " + url);


            } else {

                process = new ProcessBuilder()
                        .command("/system/bin/ping", url)
                        .redirectErrorStream(true)
                        .start();

            }

            Matcher m = null;
            Pattern pattern = Pattern.compile("time=(\\d+\\.?\\d+?) ms");
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    process.getInputStream()));

            String inputLine;
            int count = 0;

            while ((inputLine = reader.readLine()) != null && count < 3) {
                Log.i("ping", "************ inputLine  " + inputLine);
                m = pattern.matcher(inputLine);

                if (m.find()) {
                    Log.i("ping", "************ sum+  " + Float.parseFloat(m.group(1)));
                    sum += Float.parseFloat(m.group(1));
                    count++;
                }

            }
            process.destroy();
            reader.close();
            sum = sum / count;

        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.i("ping", "" + sum);

        speed = (int) (sum + 0.5);
        Log.i("ping", " speed=  " + speed);
        str = speed;
// ---------------------------------------------------------------------------
        return str;
    }


}
