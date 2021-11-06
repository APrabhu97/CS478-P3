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
   // BroadcastReceiver touristReceiver = new TouristReceiver() ;

    IntentFilter mainFilter = new IntentFilter(TOURIST_INTENT) ;
    //IntentFilter touristFilter = new IntentFilter(TOURIST_INTENT) ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFilter.addAction(RESTAURANT_INTENT);
        mainFilter.addAction(TOURIST_INTENT);

        mainFilter.setPriority(100);
        registerReceiver(mainReceiver, mainFilter, PERMISSION_NAME, null);

     //   touristFilter.setPriority(100);
      //  registerReceiver(touristReceiver, touristFilter, PERMISSION_NAME, null);
    }

    public void onDestroy() {
        super.onDestroy();
       unregisterReceiver(mainReceiver);
    //    unregisterReceiver(touristReceiver);
    }
}