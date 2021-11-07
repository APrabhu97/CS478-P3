package com.anish.a2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String RESTAURANT_INTENT =
            "edu.uic.cs478.chicago.showRestaurants";
    private static final String TOURIST_INTENT =
            "edu.uic.cs478.chicago.showTouristSpots";
    private static final String PERMISSION_NAME =
            "edu.uic.cs478.fall2021.project3";

    BroadcastReceiver mainReceiver = new MainReceiver() ;

    IntentFilter mainFilter = new IntentFilter(TOURIST_INTENT) ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ContextCompat.checkSelfPermission(this, PERMISSION_NAME) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{PERMISSION_NAME}, 1);
        }
        else{
            mainFilter.addAction(RESTAURANT_INTENT);
            mainFilter.addAction(TOURIST_INTENT);
            mainFilter.setPriority(100);
            registerReceiver(mainReceiver, mainFilter, PERMISSION_NAME, null);
        }
    }

    public void onRequestPermissionsResult(int code, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(code, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            mainFilter.addAction(RESTAURANT_INTENT);
            mainFilter.addAction(TOURIST_INTENT);
            mainFilter.setPriority(100);
            registerReceiver(mainReceiver, mainFilter, PERMISSION_NAME, null);
        } else {
            Toast.makeText(this, "A2 will not work without you granting the permission", Toast.LENGTH_LONG).show();
        }
    }

    public void onDestroy() {
        super.onDestroy();
       unregisterReceiver(mainReceiver);
    //    unregisterReceiver(touristReceiver);
    }
}