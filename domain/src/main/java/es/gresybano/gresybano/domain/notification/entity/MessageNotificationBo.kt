package es.gresybano.gresybano.domain.notification.entity

import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

private const val KEY_ID_NOTIFICATION = "idNotification"
private const val KEY_DATE_RECEIVED_NOTIFICATION = "dateReceived"

private const val KEY_ID_PUBLICATION = "id"
private const val KEY_TITLE_PUBLICATION = "title"
private const val KEY_SUBTITLE_PUBLICATION = "subtitle"
private const val KEY_MAIN_IMAGE_PUBLICATION = "mainImage"
private const val KEY_IS_OPENED_PUBLICATION = "isOpened"

private const val FORMAT_DATE = "yyyy-MM-dd HH:mm:ss"

private fun Map<String, String>.getString(key: String, defaultValue: String = "") =
    this[key] ?: defaultValue

private fun Map<String, String>.getLong(key: String, defaultValue: Long = -1L) = try {
    this[key]!!.toLong()

} catch (e: Exception) {
    defaultValue
}

sealed class MessageNotificationBo(
    open val idNotification: Long,
    open val title: String,
    open val subtitle: String,
    open var isOpened: Boolean = false,
    open var dateReceived: Date,
    var typeNotification: TypeMessageNotification,
) {

    data class NewPublicationBo(
        override val idNotification: Long,
        override val title: String,
        override val subtitle: String,
        override var isOpened: Boolean = false,
        override var dateReceived: Date,
        val id: String,
        val mainImage: String,
    ) : MessageNotificationBo(
        idNotification = idNotification,
        title = title,
        subtitle = subtitle,
        isOpened = isOpened,
        dateReceived = dateReceived,
        typeNotification = TypeMessageNotification.NEW_PUBLICATION,
    ) {

        fun createJSON(): JSONObject {
            return JSONObject()
                .put(KEY_ID_NOTIFICATION, idNotification)
                .put(KEY_TITLE_PUBLICATION, title)
                .put(KEY_SUBTITLE_PUBLICATION, subtitle)
                .put(KEY_IS_OPENED_PUBLICATION, isOpened)
                .put(KEY_ID_PUBLICATION, id)
                .put(KEY_MAIN_IMAGE_PUBLICATION, mainImage)
                .put(KEY_DATE_RECEIVED_NOTIFICATION, dateReceived.parseDateToString())
                .put("typeNotification", typeNotification)
        }

    }

    object Error : MessageNotificationBo(
        idNotification = -1L,
        title = "",
        subtitle = "",
        isOpened = true,
        dateReceived = Date(),
        typeNotification = TypeMessageNotification.ERROR_TYPE,
    )

    companion object {

        fun createNewPublicationBo(data: Map<String, String>) =
            if (
                data.containsKey(KEY_ID_NOTIFICATION) &&
                data.containsKey(KEY_ID_PUBLICATION) &&
                data.containsKey(KEY_TITLE_PUBLICATION) &&
                data.containsKey(KEY_SUBTITLE_PUBLICATION) &&
                data.containsKey(KEY_MAIN_IMAGE_PUBLICATION)
            ) {
                NewPublicationBo(
                    idNotification = data.getLong(KEY_ID_NOTIFICATION),
                    title = data.getString(KEY_TITLE_PUBLICATION),
                    subtitle = data.getString(KEY_SUBTITLE_PUBLICATION),
                    isOpened = false,
                    id = data.getString(KEY_ID_PUBLICATION),
                    mainImage = data.getString(KEY_MAIN_IMAGE_PUBLICATION),
                    dateReceived = Date(),
                )

            } else {
                Error
            }

        fun createNewPublicationBo(jsonObject: JSONObject) =
            if (
                !jsonObject.isNull(KEY_ID_NOTIFICATION) &&
                !jsonObject.isNull(KEY_ID_PUBLICATION) &&
                !jsonObject.isNull(KEY_TITLE_PUBLICATION) &&
                !jsonObject.isNull(KEY_SUBTITLE_PUBLICATION) &&
                !jsonObject.isNull(KEY_IS_OPENED_PUBLICATION) &&
                !jsonObject.isNull(KEY_MAIN_IMAGE_PUBLICATION) &&
                !jsonObject.isNull(KEY_DATE_RECEIVED_NOTIFICATION)
            ) {
                NewPublicationBo(
                    idNotification = jsonObject.getLong(KEY_ID_NOTIFICATION),
                    title = jsonObject.getString(KEY_TITLE_PUBLICATION),
                    subtitle = jsonObject.getString(KEY_SUBTITLE_PUBLICATION),
                    isOpened = jsonObject.getBoolean(KEY_IS_OPENED_PUBLICATION),
                    id = jsonObject.getString(KEY_ID_PUBLICATION),
                    mainImage = jsonObject.getString(KEY_MAIN_IMAGE_PUBLICATION),
                    dateReceived = jsonObject.getString(KEY_DATE_RECEIVED_NOTIFICATION)
                        .parseStringToDate(),
                )

            } else {
                Error
            }

        private fun Date.parseDateToString(): String {
            return try {
                SimpleDateFormat(FORMAT_DATE).format(this)

            } catch (e: Exception) {
                ""
            }
        }

        private fun String.parseStringToDate(): Date {
            return try {
                SimpleDateFormat(FORMAT_DATE).parse(this)

            } catch (e: Exception) {
                Date()
            }
        }

    }

}

fun MessageNotificationBo.getMainImage() =
    when (this) {
        is MessageNotificationBo.NewPublicationBo -> mainImage
        else -> ""
    }