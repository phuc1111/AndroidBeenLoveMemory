package com.example.androidbeenlovememory;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface UserDao {
    @Query("SELECT * FROM User")
    List<User> getAll();
    @Insert
    void insertAll(User... users);
    @Delete
    void delete(User user);
}
