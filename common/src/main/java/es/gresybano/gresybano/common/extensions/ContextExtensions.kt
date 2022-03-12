package es.gresybano.gresybano.common.extensions

import android.content.Context
import android.content.res.ColorStateList
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat


fun Context.getColorState(@ColorRes color: Int) =
    ColorStateList.valueOf(ContextCompat.getColor(this, color))

fun Context.getDrawableCompat(@DrawableRes drawable: Int) =
    AppCompatResources.getDrawable(this, drawable)