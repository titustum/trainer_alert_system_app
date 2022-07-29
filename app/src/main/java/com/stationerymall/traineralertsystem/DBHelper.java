package com.stationerymall.traineralertsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String ALERTS_TABLE = "_alerts";
    private static final String KEY_ID = "alert_id";
    private static final String KEY_DRUGNAME = "alert_drugname";
    private static final String KEY_DRUGNO = "alert_drugno";
    private static final String KEY_TIME = "alert_time";
    private static final String KEY_DATE = "alert_date";
    SQLiteDatabase db;
    private static final String DATABASE_NAME = "remindersDatabase.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+ALERTS_TABLE+ "("
                +KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +KEY_DRUGNAME+" TEXT, "
                +KEY_DRUGNO+" TEXT, "
                +KEY_DATE+" TEXT, "
                +KEY_TIME+" TEXT);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ALERTS_TABLE);
        onCreate(db);
    }

    public long insertAlert(String drugname, String drugno, String date, String time){
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_DRUGNAME, drugname);
        contentValues.put(KEY_DRUGNO, drugno);
        contentValues.put(KEY_DATE, date);
        contentValues.put(KEY_TIME, time);
        return db.insert(ALERTS_TABLE, null, contentValues);
    }

    public ArrayList<Alert> getAlertObjects(){
        ArrayList<Alert> alerts = new ArrayList<>();
        db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM "+ALERTS_TABLE+" ORDER BY "+KEY_TIME+" DESC", null);

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            alerts.add(new Alert(cursor.getString(4),cursor.getString(2), cursor.getString(1), false));
        }
        return  alerts;
    };
}
