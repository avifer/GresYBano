package es.gresybano.gresybano.common.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadUrl(url: String, width: Int? = null, height: Int? = null) {
    if (width != null && height != null) {
        Glide.with(context).load(url).override(width, height).into(this)

    } else {
        Glide.with(context).load(url).into(this)
    }
}