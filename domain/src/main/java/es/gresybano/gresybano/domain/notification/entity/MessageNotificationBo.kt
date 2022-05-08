package es.gresybano.gresybano.domain.notification.entity

private const val KEY_ID_NOTIFICATION = "idNotification"

private const val KEY_ID_PUBLICATION = "id"
private const val KEY_TITLE_PUBLICATION = "title"
private const val KEY_SUBTITLE_PUBLICATION = "subtitle"
private const val KEY_MAIN_IMAGE_PUBLICATION = "mainImage"

private fun Map<String, String>.getString(key: String, defaultValue: String = "") =
    this[key] ?: defaultValue

private fun Map<String, String>.getLong(key: String, defaultValue: Long = -1L) = try {
    this[key]!!.toLong()

} catch (e: Exception) {
    defaultValue
}

sealed class MessageNotificationBo {

    data class NewPublicationBo(
        val idNotification: Long,
        val title: String,
        val subtitle: String,
        var isOpened: Boolean = false,
        val id: Long,
        val mainImage: String,
    ) : MessageNotificationBo()

    object Error : MessageNotificationBo()

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
                    id = data.getLong(KEY_ID_PUBLICATION),
                    mainImage = data.getString(KEY_MAIN_IMAGE_PUBLICATION),
                )

            } else {
                Error
            }

    }
}

fun MessageNotificationBo.getIdNotification() =
    when (this) {
        is MessageNotificationBo.NewPublicationBo -> idNotification
        else -> -1L
    }

fun MessageNotificationBo.getTitle() =
    when (this) {
        is MessageNotificationBo.NewPublicationBo -> title
        else -> ""
    }

fun MessageNotificationBo.getSubtitle() =
    when (this) {
        is MessageNotificationBo.NewPublicationBo -> subtitle
        else -> ""
    }

fun MessageNotificationBo.isOpened() =
    when (this) {
        is MessageNotificationBo.NewPublicationBo -> isOpened
        else -> true
    }

fun MessageNotificationBo.getMainImage() =
    when (this) {
        is MessageNotificationBo.NewPublicationBo -> mainImage
        else -> ""
    }



