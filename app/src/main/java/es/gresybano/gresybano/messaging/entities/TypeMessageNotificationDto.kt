package es.gresybano.gresybano.messaging.entities

import es.gresybano.gresybano.common.util.EMPTY_STRING

enum class TypeMessageNotificationDto {
    NEW_PUBLICATION, ERROR_TYPE;

    companion object {

        fun parseTypeMessage(type: String?) =
            try {
                valueOf(type ?: EMPTY_STRING)

            } catch (e: Exception) {
                ERROR_TYPE
            }

    }

}