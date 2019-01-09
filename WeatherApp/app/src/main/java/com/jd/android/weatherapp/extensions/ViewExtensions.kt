package com.jd.android.weatherapp.extensions

import android.view.View
import android.widget.TextView

var TextView.textColor: Int
    get() = currentTextColor
    set(color) = setTextColor(color)

fun View.slideExit() {
    if (translationY == 0f) animate().translationY(-height.toFloat())
}

fun View.slideEnter() {
    if (translationY < 0f) animate().translationY(0f)
}