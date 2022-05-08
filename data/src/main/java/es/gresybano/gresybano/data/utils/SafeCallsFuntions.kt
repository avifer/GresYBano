package es.gresybano.gresybano.data.utils

import es.gresybano.gresybano.domain.response.CodeExceptions
import es.gresybano.gresybano.domain.response.ExceptionInfo
import es.gresybano.gresybano.domain.response.Response
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

suspend fun <T> safeRemoteCall(block: suspend () -> T): Response<T> {
    return try {
        Response.Successful(block())
    } catch (e: Exception) {
        return Response.Error(
            ExceptionInfo(
                when (e) {
                    is ConnectException -> {
                        CodeExceptions.CONNECT_EXCEPTION
                    }
                    is UnknownHostException -> {
                        CodeExceptions.UNKNOWN_HOST_EXCEPTION
                    }
                    is SocketTimeoutException -> {
                        CodeExceptions.SOCKET_TIME_OUT_EXCEPTION
                    }
                    is HttpException -> {
                        CodeExceptions.UNKNOWN_NETWORK_EXCEPTION
                    }
                    else -> {
                        CodeExceptions.UNKNOWN_NETWORK_EXCEPTION
                    }
                },
                e.message
            )
        )
    }
}

suspend fun <T> safeLocalCall(block: suspend () -> T): Response<T> {
    return try {
        Response.Successful(block())
    } catch (e: Exception) {
        return Response.Error(
            ExceptionInfo(
                CodeExceptions.DATABASE_ROOM_ERROR,
                e.message
            )
        )
    }
}