package com.example.loactionwalaapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE latitude LIKE :latitude AND " +
            "longitude LIKE :longitude LIMIT 1")
    User findByName(double latitude, double longitude);



    @Insert
    void insertLocation(User users);

    @Update
    void Update(User users);

    @Delete
    void delete(User user);
}