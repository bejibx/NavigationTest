package com.example.bejibx.navigationtest.ext

import android.graphics.drawable.Drawable
import android.widget.TextView

private const val DRAWABLE_INDEX_LEFT = 0
private const val DRAWABLE_INDEX_TOP = 1
private const val DRAWABLE_INDEX_RIGHT = 2
private const val DRAWABLE_INDEX_BOTTOM = 3

var TextView.compoundDrawableLeft: Drawable?
    get() = compoundDrawables[DRAWABLE_INDEX_LEFT]
    set(value) {
        setCompoundDrawablesWithIntrinsicBounds(
                value,
                compoundDrawableTop,
                compoundDrawableRight,
                compoundDrawableBottom
        )
    }

var TextView.compoundDrawableTop: Drawable?
    get() = compoundDrawables[DRAWABLE_INDEX_TOP]
    set(value) {
        setCompoundDrawablesWithIntrinsicBounds(
                compoundDrawableLeft,
                value,
                compoundDrawableRight,
                compoundDrawableBottom
        )
    }

var TextView.compoundDrawableRight: Drawable?
    get() = compoundDrawables[DRAWABLE_INDEX_RIGHT]
    set(value) {
        setCompoundDrawablesWithIntrinsicBounds(
                compoundDrawableLeft,
                compoundDrawableTop,
                value,
                compoundDrawableBottom
        )
    }

var TextView.compoundDrawableBottom: Drawable?
    get() = compoundDrawables[DRAWABLE_INDEX_BOTTOM]
    set(value) {
        setCompoundDrawablesWithIntrinsicBounds(
                compoundDrawableLeft,
                compoundDrawableTop,
                compoundDrawableRight,
                value
        )
    }