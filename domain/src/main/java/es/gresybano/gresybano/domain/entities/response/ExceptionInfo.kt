package es.gresybano.gresybano.domain.entities.response

import es.gresybano.gresybano.domain.R

data class ExceptionInfo(
    val code: CodeExceptions?,
    val message: String? = null,
)

fun ExceptionInfo.getStringError(): Int {
    return when (code) {
        CodeExceptions.CONNECT_EXCEPTION -> R.string.connect_error
        CodeExceptions.UNKNOWN_HOST_EXCEPTION -> R.string.unknown_host_error
        CodeExceptions.SOCKET_TIME_OUT_EXCEPTION -> R.string.socket_time_out_error
        CodeExceptions.UNKNOWN_NETWORK_EXCEPTION -> R.string.unknown_network_error
        CodeExceptions.DATABASE_ROOM_ERROR -> R.string.unknown_network_error
        null -> R.string.unknown_network_error
    }
}