package com.stationerymall.traineralertsystem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    List<Alert> alerts;
    MainAdapter adapter;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list_view_alert);

        listAlerts();

        adapter = new MainAdapter(this, alerts);
        listView.setAdapter(adapter);

        FloatingActionButton floatingActionButton = findViewById(R.id.add_alert_floating_action_button);
        floatingActionButton.setOnClickListener(View->{
            Intent intent = new Intent(MainActivity.this, AddAlertActivity.class);
            startActivity(intent);
        });


    }

    private void listAlerts() {
        dbHelper = new DBHelper(this);
        alerts = dbHelper.getAlertObjects();
    }

    @Override
    protected void  onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }

}