package com.heyrex.mytourismapp.core.extensions

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun ImageView.loadImage(url: String) {
    Glide.with(context).load(url).into(this)
}