package com.example.bejibx.navigationtest.ext

import android.content.Context
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat

fun Context.getDrawableCompat(@DrawableRes drawableResourceId: Int) =
        ContextCompat.getDrawable(this, drawableResourceId)