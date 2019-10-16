package com.example.androidbeenlovememory;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;
import java.sql.Time;
import java.util.Timer;
@Entity
public class User {
    @PrimaryKey
    public int uid;
    public String name1;
    public String name2;
    public int date;
}
