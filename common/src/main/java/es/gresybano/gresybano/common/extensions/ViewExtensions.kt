package es.gresybano.gresybano.common.extensions

import android.view.View
import android.view.View.*

fun View.invisible() {
    this.visibility = GONE
}

fun View.hide() {
    this.visibility = INVISIBLE
}

fun View.show() {
    this.visibility = VISIBLE
}

fun View.visible(visible: Boolean) {
    this.visibility =
        if (visible) {
            VISIBLE
        } else {
            GONE
        }
}

