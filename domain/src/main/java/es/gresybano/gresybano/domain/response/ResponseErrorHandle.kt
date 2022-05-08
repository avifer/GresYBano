package es.gresybano.gresybano.domain.response

data class ResponseErrorHandle(
    val idResError: Int,
    var handled: Boolean,
) {
    fun getIdResErrorHandle(): Int? {
        return if (handled) {
            null

        } else {
            handled = true
            idResError
        }
    }
}