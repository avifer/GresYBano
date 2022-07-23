package es.gresybano.gresybano.common.extensions

import java.util.*

fun Calendar.putInLastHour() = this.apply {
    set(Calendar.HOUR_OF_DAY, 23)
    set(Calendar.MINUTE, 59)
    set(Calendar.SECOND, 59)
    set(Calendar.MILLISECOND, 59)
}

fun Calendar.putDate(date: Date) = this.apply {
    time = date
}