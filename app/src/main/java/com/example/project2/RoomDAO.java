package com.example.project2;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RoomDAO {

    @Insert
    void insert(RoomUsers roomUsers);

    @Query("select * from RoomUsers")
    List<RoomUsers> getAllUsers();
}
