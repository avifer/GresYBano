package es.gresybano.gresybano.common.util

import java.util.*

fun getCalendar(date: Date): Calendar = Calendar.getInstance().apply {
    time = date
}