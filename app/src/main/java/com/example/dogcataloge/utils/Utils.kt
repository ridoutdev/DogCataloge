package com.example.dogcataloge.utils

import android.widget.ImageView
import com.squareup.picasso.Picasso

object Utils {

    fun loadImage(url: String, layout: ImageView) {
        Picasso.get().load(url).into(layout)
    }

}