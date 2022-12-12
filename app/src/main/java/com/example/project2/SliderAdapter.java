package com.example.project2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.Objects;

public class SliderAdapter extends PagerAdapter {
    Context context;
    int images[];
    ImageView imageView;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context, int[] images) {
        this.context = context;
        this.images = images;
        layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View itemview = layoutInflater.inflate(R.layout.imageforviewpager, container, false);
        imageView = (ImageView) itemview.findViewById(R.id.sliders);
        imageView.setImageResource(images[position]);
        Objects.requireNonNull(container).addView(itemview);

        return itemview;
    }
}
