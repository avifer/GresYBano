package es.gresybano.gresybano.domain.response

data class ResponseLoadingHandle(
    val loading: Boolean,
    var handled: Boolean,
) {
    fun getLoadingHandle(): Boolean? {
        return if (handled) {
            null

        } else {
            handled = true
            loading
        }
    }
}