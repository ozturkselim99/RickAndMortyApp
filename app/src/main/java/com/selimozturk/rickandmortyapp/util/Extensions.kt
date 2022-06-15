package com.selimozturk.rickandmortyapp.util

import android.content.Context
import android.view.View
import android.widget.Toast

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun View.setVisible(visible: Boolean) {
    visibility = if (visible) {
        View.VISIBLE
    } else {
        View.INVISIBLE
    }
}