package es.gresybano.gresybano.domain.entities.response

sealed class Response<T> {
    data class Successful<T>(val data: T? = null) : Response<T>()
    data class Error<T>(val error: ExceptionInfo) : Response<T>()
    data class Loading<T>(val loading: Boolean = true) : Response<T>()
}

fun <T> Response<T>.getData() = (this as? Response.Successful)?.data

fun <T> Response<T>.getError() = (this as? Response.Error)?.error

fun <T> Response<T>.isDataNull() = (this as? Response.Successful)?.data == null

fun <T> Response<T>.isSuccessful() = (this is Response.Successful)

fun <T> Response<T>.isError() = (this is Response.Error)

fun <T> Response<T>.isWaiting() = (this is Response.Loading)

fun <T> Response.Error<T>.getStringError() = error.getStringError()