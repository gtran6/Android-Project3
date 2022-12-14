package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class DeliveryActivity extends AppCompatActivity {
    TextView uName, uHomeAddress, uWorkAddress;

    RoomDatabaseUsers databaseUsers;
    RoomDAO roomDAO;

    List<RoomUsers> roomUsers;

    RoomUsers users = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        uName = (TextView) findViewById(R.id.uName);
        uHomeAddress = (TextView) findViewById(R.id.uHomeAddress);
        uWorkAddress = (TextView) findViewById(R.id.uWorkAddress);

        databaseUsers = RoomDatabaseUsers.getInstance(this);
        roomDAO = databaseUsers.getDAO();
        roomUsers = roomDAO.getAllUsers();

        for (RoomUsers u : roomUsers) {
            if (MainActivity.currentUser.equals(u.getUserName())) {
                users = u;
                break;
            }
        }
        uName.setText(users.getName());
        uHomeAddress.setText(users.getUserHomeAddress());
        uWorkAddress.setText(users.getUserWorkAddress());

    }
}