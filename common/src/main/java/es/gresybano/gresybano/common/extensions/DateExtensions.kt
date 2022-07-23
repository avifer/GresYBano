package es.gresybano.gresybano.common.extensions

import android.content.Context
import es.gresybano.gresybano.common.R
import es.gresybano.gresybano.common.util.getCalendar
import java.util.*

fun Date.getToString(context: Context): String {
    var format =
        if (getCalendar(Date()).get(Calendar.YEAR) == getCalendar(this).get(Calendar.YEAR)) {
            context.getString(R.string.format_date_without_year)

        } else {
            context.getString(R.string.format_date_with_year)
        }

    format =
        format.replace("{d}", getCalendar(this@getToString).get(Calendar.DAY_OF_MONTH).toString())

    format = format.replace(
        "{m}",
        context.getString(
            when (getCalendar(this@getToString).get(Calendar.MONTH)) {
                0 -> R.string.january
                1 -> R.string.february
                2 -> R.string.march
                3 -> R.string.april
                4 -> R.string.may
                5 -> R.string.june
                6 -> R.string.july
                7 -> R.string.august
                8 -> R.string.september
                9 -> R.string.octuber
                10 -> R.string.november
                else -> R.string.december
            }
        )
    )

    format = format.replace("{y}", getCalendar(this@getToString).get(Calendar.YEAR).toString())

    return format
}