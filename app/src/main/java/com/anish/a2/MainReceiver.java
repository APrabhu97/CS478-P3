package com.anish.a2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.HashMap;
import java.util.Map;

public class MainReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, AttractionActivity.class);
        i.putExtra("activityType", intent.getStringExtra("activityType"));
        context.startActivity(i);
    }
}
