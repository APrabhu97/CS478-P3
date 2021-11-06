package com.anish.a2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class TouristReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Companion receiver in action! ",
                Toast.LENGTH_LONG).show() ;
        context.startActivity(new Intent(context, AttractionActivity.class));
    }
}
