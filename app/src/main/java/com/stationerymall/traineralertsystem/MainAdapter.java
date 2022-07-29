package com.stationerymall.traineralertsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MainAdapter extends BaseAdapter {

    Context context;
    List<Alert> alerts;

    public MainAdapter(Context context, List<Alert> alerts) {
        this.context = context;
        this.alerts = alerts;
    }

    @Override
    public int getCount() {
        return alerts.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        System.out.println(alerts.get(position).getDrugname());
        System.out.println(alerts.get(position).getDrugnumber());
        System.out.println(alerts.get(position).getTime());

        view = LayoutInflater.from(context).inflate(R.layout.list_alerts, parent, false);

        TextView drugnameTextView = view.findViewById(R.id.drugno_alerted);
        TextView drugnoTextView = view.findViewById(R.id.drugname_alerted);
        TextView timeTextView = view.findViewById(R.id.time_alerted);
        ImageView bellIcon = view.findViewById(R.id.bell_image_icon);

        drugnameTextView.setText(alerts.get(position).getDrugname());
        drugnoTextView.setText(alerts.get(position).getDrugnumber());
        timeTextView.setText(alerts.get(position).getTime());

        if (alerts.get(position).isCompleted()){
            bellIcon.setImageResource(R.drawable.alarm_icon_foreground);
        }else{
            bellIcon.setImageResource(R.drawable.alarm_icon_foreground);
        }

        return view;
    }
}
