package es.gresybano.gresybano.common.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun runInIO(block: suspend () -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
        block()
    }
}

fun runInMain(block: suspend () -> Unit) {
    CoroutineScope(Dispatchers.Main).launch {
        block()
    }
}

fun runDelayMain(delay: Long, block: () -> Unit) {
    runInIO {
        Thread.sleep(delay)
        runInMain {
            block()
        }
    }
}

fun runDelayIO(delay: Long, block: () -> Unit) {
    runInIO {
        Thread.sleep(delay)
        block()
    }
}

fun parseToJSON(element: Any?): String = Gson().toJson(element)

fun <T> parseJSON(element: String?, clazz: Class<T>) =
    try {
        GsonBuilder().create().fromJson(element, clazz)

    } catch (e: Exception) {
        null
    }