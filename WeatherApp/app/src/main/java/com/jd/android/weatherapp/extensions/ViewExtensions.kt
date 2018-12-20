package com.jd.android.weatherapp.extensions

import android.widget.TextView

var TextView.textColor: Int
    get() = currentTextColor
    set(color) = setTextColor(color)