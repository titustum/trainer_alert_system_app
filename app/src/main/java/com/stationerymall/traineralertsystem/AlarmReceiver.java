package com.stationerymall.traineralertsystem;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Alarm received!", Toast.LENGTH_SHORT).show();

        Intent intentToRingtone = new Intent(context, RingtoneService.class);
        context.startService(intentToRingtone);
    }
}
