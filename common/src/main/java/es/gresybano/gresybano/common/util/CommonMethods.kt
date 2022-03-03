package es.gresybano.gresybano.common.util

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