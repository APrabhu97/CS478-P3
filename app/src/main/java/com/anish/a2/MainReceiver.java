package com.anish.a2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.HashMap;
import java.util.Map;

public class MainReceiver extends BroadcastReceiver {
    Map<String, Class<?>> activityMap = new HashMap<String, Class<?>>() {{
        put("restaurant", AttractionActivity.class);
        put("tourist", TouristActivity.class);
    }};
    @Override
    public void onReceive(Context context, Intent intent) {
        context.startActivity(new Intent(context, activityMap.get(intent.getStringExtra("activityType"))));
    }
}
