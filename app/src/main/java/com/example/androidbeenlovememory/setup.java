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
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.util.List;

public class setup extends AppCompatActivity {
    AppDatabase db;
    Button bt;
    String name1, name2, bt_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        bt = findViewById(R.id.bt_setDate);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();

            }
        });

        Button bt_submit = findViewById(R.id.bt_submit);
        bt_submit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onClick(View view) {
                    setInfor();
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    private void setInfor() {
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();

        EditText editTextNam1 = findViewById(R.id.edt_name1);
        EditText editTextNam2 = findViewById(R.id.edt_name2);
        Button bt_day = findViewById(R.id.bt_setDate);

        name1 = editTextNam1.getText().toString();
        name2 = editTextNam2.getText().toString();
        bt_date = bt_day.getText().toString();

            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    User user = new User();
                    user.uid = 8;
                    user.name1 = name1;
                    user.name2 = name2;
                    user.date = bt_date;
                    db.userDao().insertAll(user);
                    return null;
                }
                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    moveMainScreen();
                }
            }.execute();
    }

    private void showDatePicker() {
        DatePickerDialog date = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                bt = findViewById(R.id.bt_setDate);
                bt.setText(i + "-" + (i1 + 1) + "-" + i2 + " 00:00:00");
            }
        }, 2019, 01, 01);
        date.show();
    }

    private void moveMainScreen() {
        Intent intent = new Intent(setup.this, MainActivity.class);
        startActivity(intent);
    }
}
