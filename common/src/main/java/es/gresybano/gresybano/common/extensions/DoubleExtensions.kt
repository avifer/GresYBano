package es.gresybano.gresybano.common.extensions

import java.math.RoundingMode
import java.text.DecimalFormat

private const val PATTERN_TWO_DECIMALS = "#,###.00"

fun Double.toFormatTwoDecimal(): String {
    var result = "0"
    if (this > 0) {
        val df = DecimalFormat(PATTERN_TWO_DECIMALS)
        df.roundingMode = RoundingMode.DOWN
        result = df.format(this)
    }
    return result
}