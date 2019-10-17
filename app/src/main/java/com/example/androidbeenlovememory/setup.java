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

public class setup extends AppCompatActivity {
    AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        Button bt = findViewById(R.id.bt_setDate);
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
                moveMainScreen();
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    private  void setInfor(){
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        //@SuppressLint("WrongViewCast") DatePicker datePicker = findViewById(R.id.bt_setDate);
                        EditText editTextNam1 = findViewById(R.id.edt_name1);
                        EditText editTextNam2 = findViewById(R.id.edt_name2);
                        String name1 = editTextNam1.getText().toString();
                        String name2 = editTextNam2.getText().toString();
                        //Long date = (DatePicker) findViewById(R.id.bt_setDate).getMaxDate();

                        User user = new User();
                        user.uid=1;
                        user.name1=name1;
                        user.name2=name2;
                        //user.date= ;
                        db.userDao().insertAll(user);
                    }
                });
                return null;

            }
        }.execute();
    }

    private void showDatePicker(){
        DatePickerDialog date = new DatePickerDialog(this , new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                //Log.i("TAG", "" +i + "" +i1 + " "+i2);
                Button bt = findViewById(R.id.bt_setDate);
                bt.setText(i+" "+(i1+1)+" "+i2);
            }
        }, 2019, 01, 01);
        date.show();
    }

    private void moveMainScreen(){
        Intent intent = new Intent(setup.this, MainActivity.class);
        startActivity(intent);
    }
}
