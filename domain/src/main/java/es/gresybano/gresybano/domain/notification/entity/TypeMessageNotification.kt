package es.gresybano.gresybano.domain.notification.entity

enum class TypeMessageNotification {
    NEW_PUBLICATION, ERROR_TYPE;

    companion object {

        fun parseTypeMessage(type: String?) =
            try {
                valueOf(type ?: "")

            } catch (e: Exception) {
                ERROR_TYPE
            }

    }

}