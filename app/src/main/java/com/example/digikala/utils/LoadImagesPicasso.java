package com.example.digikala.utils;

import android.widget.ImageView;

import com.example.digikala.R;
import com.squareup.picasso.Picasso;

public class LoadImagesPicasso {
    public static void loadImage(String src, ImageView target) {
        Picasso.get()
                .load(src)
                .placeholder(R.drawable.logo)
                .into(target);
    }
}
