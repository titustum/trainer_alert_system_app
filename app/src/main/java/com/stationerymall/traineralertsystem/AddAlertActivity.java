package com.stationerymall.traineralertsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AddAlertActivity extends AppCompatActivity {
    private static final int RQS_1 = 1;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alert);

        EditText alertDrugname = findViewById(R.id.input_alert_drugname);
        EditText alertDrugno = findViewById(R.id.input_alert_number_of_drugs);
        DatePicker alertDate = findViewById(R.id.input_alert_date_time);
        TimePicker timeAlerted = findViewById(R.id.input_alert_time);
        Button addAlertButton = findViewById(R.id.add_alert_button);

        dbHelper=new DBHelper(this);

        addAlertButton.setOnClickListener(View->{
            String fullTime;
            if (Build.VERSION.SDK_INT >=23){
                fullTime = String.valueOf((timeAlerted.getHour()))+":"+timeAlerted.getMinute();
            }else{
                fullTime = String.valueOf((timeAlerted.getCurrentHour()))+":"+timeAlerted.getCurrentMinute();
            }

            int day = alertDate.getDayOfMonth();
            int month = alertDate.getMonth() + 1;
            int year = alertDate.getYear();

            String fullDate = day +"/"+month+"/"+year;

            dbHelper.insertAlert(alertDrugname.getText().toString(), alertDrugno.getText().toString(), fullDate, fullTime);

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, timeAlerted.getCurrentHour());
            calendar.set(Calendar.MINUTE, timeAlerted.getCurrentMinute());
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            Intent intent = new Intent(this, AlarmReceiver.class);

            pendingIntent = PendingIntent.getBroadcast(getBaseContext(), RQS_1, intent, 0);
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

            Toast.makeText(this, "Alert set successfully!", Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, "Subject: "+alertSubject.getText()+" Day: "+fullDate+" Time: "+fullTime, Toast.LENGTH_SHORT).show();
        });

    }
}