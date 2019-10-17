package com.example.androidbeenlovememory;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

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
//                User user = new User();
//                user.uid= 1;
//                user.name1 = "thien phuc ";
//                user.name2 = "van tao ";
//                user.date = 2019-10-10;
//                db.userDao().insertAll(user);
                if (getSizeUser()==0){
                    Intent intent = new Intent(MainActivity.this, setup.class);
                    startActivity(intent);
                }
                return null;
            }
        }.execute();
        setInfor();

    }
    @SuppressLint("StaticFieldLeak")
    private void setInfor(){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                final List <User> users = db.userDao().getAll();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView name1 = findViewById(R.id.tv_name1);
                        TextView name2 = findViewById(R.id.tv_name2);
                        TextView day = findViewById(R.id.tv_day);
                        if (users.size()>0){
                            name1.setText(users.get(0).name1);
                            name2.setText(users.get(0).name2);
                            day.setText(users.get(0).name2);
                        }
                    }
                });
                return null;
            }
        }.execute();
    }
    private int getSizeUser(){
        final List <User> users = db.userDao().getAll();
        return users.size();
    }
}
