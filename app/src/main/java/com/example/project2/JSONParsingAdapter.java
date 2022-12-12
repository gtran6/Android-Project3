package com.example.project2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.net.URL;
import java.util.List;

public class JSONParsingAdapter extends ArrayAdapter<FoodItems> {
    TextView name;
    ImageView image;
    List<FoodItems> foodList;

    private Context context;

    public JSONParsingAdapter(@NonNull Context context, List<FoodItems> foodList) {
        super(context, R.layout.jsoncustomview, foodList);
        this.context = context;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.jsoncustomview, null);
        }

        image = convertView.findViewById(R.id.imageview);
        name = convertView.findViewById(R.id.productname);

        FoodItems foodItems = foodList.get(position);

        name.setText(foodItems.getName());

        try {
            URL glideImageURL = new URL(foodItems.getImageurl());
            Glide.with(context).load(glideImageURL).into(image);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return convertView;
    }
}
