package com.example.androidbeenlovememory;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    AppDatabase db;

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                if (getSizeUser() == 0) {
                    Intent intent = new Intent(MainActivity.this, setup.class);
                    startActivity(intent);
                    Log.i("tag", "abc");
                } else {
                    setInfor();
                }
                return null;
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    private void setInfor() {
        new AsyncTask<Void, Void, List<User>>() {
            @Override
            protected List<User> doInBackground(Void... voids) {
                final List<User> users = db.userDao().getAll();
                return users;
            }

            @Override
            protected void onPostExecute(List<User> users) {
                super.onPostExecute(users);
                TextView name1 = findViewById(R.id.tv_name1);
                TextView name2 = findViewById(R.id.tv_name2);
                TextView tv_daylove = findViewById(R.id.day_love);
                if (users.size() > 0) {
                    name1.setText(users.get(0).name1);
                    name2.setText(users.get(0).name2);
                    tv_daylove.setText(users.get(0).date);
                }
                try {
                    setTime(users.get(0).date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }.execute();
    }
    private int getSizeUser() {
        final List<User> users = db.userDao().getAll();
        return users.size();
    }

    @SuppressLint("StaticFieldLeak")
    private void setTime(String toyBornTime) throws ParseException {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        try {
            Date oldDate = dateFormat.parse(toyBornTime);
            System.out.println(oldDate);
            Date currentDate = new Date();
            final Long diff = currentDate.getTime() - oldDate.getTime();

            if (oldDate.before(currentDate)) {

                Calendar c = Calendar.getInstance();
                c.setTimeInMillis(diff); //truyen vao
                int mYear = c.get(Calendar.YEAR) - 1970;
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH) - 1;
                int hr = c.get(Calendar.HOUR);
                int min = c.get(Calendar.MINUTE);
                int sec = c.get(Calendar.SECOND);
                Long day = diff/86400000;

                Button bt_year = findViewById(R.id.bt_nam);
                Button bt_month = findViewById(R.id.bt_thang);
                Button bt_day = findViewById(R.id.bt_ngay);
                Button bt_hour = findViewById(R.id.bt_gio);
                Button bt_minute = findViewById(R.id.bt_phut);
                Button bt_second = findViewById(R.id.bt_giay);
                TextView tv_day = findViewById(R.id.tv_day);


                bt_year.setText(String.valueOf(mYear));
                bt_month.setText(String.valueOf(mMonth));
                bt_day.setText(String.valueOf(mDay));
                bt_hour.setText(String.valueOf(hr));
                bt_minute.setText(String.valueOf(min));
                bt_second.setText(String.valueOf(sec));
                tv_day.setText(String.valueOf(day));

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


}


