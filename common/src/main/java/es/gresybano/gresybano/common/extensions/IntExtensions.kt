package es.gresybano.gresybano.common.extensions

import es.gresybano.gresybano.common.util.ZERO

fun Int?.isUpperToZero() = this?.let {
    it > ZERO
} ?: false
