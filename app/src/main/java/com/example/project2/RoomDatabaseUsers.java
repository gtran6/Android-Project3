package com.example.project2;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {RoomUsers.class}, version = 1)

public abstract class RoomDatabaseUsers extends RoomDatabase {
    public abstract RoomDAO getDAO();

    public static RoomDatabaseUsers INSTANCE;
    public static RoomDatabaseUsers getInstance(Context context) {
        try {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                RoomDatabaseUsers.class, "UserData")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return INSTANCE;
    }

}
