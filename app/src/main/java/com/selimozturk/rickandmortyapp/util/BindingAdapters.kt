package com.selimozturk.rickandmortyapp.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("downloadImageUrl")
fun downloadImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(view).load(url).into(view)
    }
}