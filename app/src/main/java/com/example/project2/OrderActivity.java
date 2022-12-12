package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class OrderActivity extends AppCompatActivity {
    RadioButton deliveryButton, takeawayButton, dineinButton;

    ViewPager viewPager;
    SliderAdapter sliderAdapter;

    int images[] = {R.drawable.food1, R.drawable.food2};

    JSONParser jsonParser;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        deliveryButton = (RadioButton) findViewById(R.id.deliveryButton);
        takeawayButton = (RadioButton) findViewById(R.id.takeawayButton);
        dineinButton = (RadioButton) findViewById(R.id.dineinButton);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        sliderAdapter = new SliderAdapter(OrderActivity.this, images);
        viewPager.setAdapter(sliderAdapter);

        Intent intent = getIntent();
        RoomUsers roomUsers = (RoomUsers) getIntent().getSerializableExtra("user-detail");

        takeawayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });

        deliveryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderActivity.this, DeliveryActivity.class);
                intent.putExtra("details", roomUsers);
                startActivity(intent);
            }
        });

        dineinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderActivity.this, DineinActivity.class);
                startActivity(intent);
            }
        });

        try {
            jsonParser = (JSONParser) new JSONParser(this, this).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}