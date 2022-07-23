package es.gresybano.gresybano.data.repository

import es.gresybano.gresybano.data.preferences.notification.NotificationPreferencesDataSource
import es.gresybano.gresybano.data.utils.BaseRepository
import es.gresybano.gresybano.domain.notification.entity.MessageNotificationBo
import es.gresybano.gresybano.domain.notification.entity.MessageNotificationBo.Companion.createNewPublicationBo
import es.gresybano.gresybano.domain.notification.entity.MessageNotificationBo.Error
import es.gresybano.gresybano.domain.notification.entity.MessageNotificationBo.NewPublicationBo
import es.gresybano.gresybano.domain.notification.entity.TypeMessageNotification.Companion.parseTypeMessage
import es.gresybano.gresybano.domain.notification.entity.TypeMessageNotification.ERROR_TYPE
import es.gresybano.gresybano.domain.notification.entity.TypeMessageNotification.NEW_PUBLICATION
import es.gresybano.gresybano.domain.notification.repository.RepositoryNotification
import es.gresybano.gresybano.domain.response.*
import org.json.JSONArray

class RepositoryNotificationImpl(
    private val notificationPreferencesDataSource: NotificationPreferencesDataSource
) : RepositoryNotification, BaseRepository() {

    override suspend fun getAllNotifications(): Response<List<MessageNotificationBo>> {
        return notificationPreferencesDataSource.getAllNotifications().defaultResponse {
            it?.let {
                parseStringToListNotifications(it)

                //Nunca deberia llegar null la respuesta. Coloco listOf mejor que usar !!
            } ?: listOf()
        }
    }

    override suspend fun saveAllNotification(listNotifications: List<MessageNotificationBo>): Response<Boolean> {
        return notificationPreferencesDataSource.setAllNotifications(
            listNotifications.map { message ->
                when (message) {
                    is NewPublicationBo -> message.createJSON()
                    else -> {}
                }
            }.toString()
        )
    }

    override suspend fun saveNotification(notificationBo: MessageNotificationBo): Response<Boolean> {
        return when (val notifications = notificationPreferencesDataSource.getAllNotifications()) {
            is Response.Successful -> {

                notifications.data?.let { listInString ->
                    val listNotifications =
                        parseStringToListNotifications(listInString).toMutableList()

                    listNotifications.add(notificationBo)

                    notificationPreferencesDataSource.setAllNotifications(
                        listNotifications.map { message ->
                            when (message) {
                                is NewPublicationBo -> message.createJSON()
                                else -> {}
                            }
                        }.toString()
                    )

                } ?: Response.Error(ExceptionInfo(CodeExceptions.UNKNOWN, null))
            }
            else -> Response.Error(ExceptionInfo(CodeExceptions.UNKNOWN, null))
        }
    }

    override suspend fun markNotificationOpened(idNotification: Long): Response<Boolean> {
        return notificationPreferencesDataSource.getAllNotifications().getData()
            ?.let { listInString ->

                notificationPreferencesDataSource.setAllNotifications(
                    parseStringToListNotifications(listInString).map {
                        if (it.idNotification == idNotification) {
                            it.isOpened = true
                        }
                        when (it) {
                            is NewPublicationBo -> it.createJSON()
                            else -> {}
                        }
                    }.toString()
                )

            } ?: Response.Successful(false)
    }

    private fun parseStringToListNotifications(listInString: String): List<MessageNotificationBo> {
        val listNotifications = mutableListOf<MessageNotificationBo>()
        try {
            JSONArray(listInString)

        } catch (e: Exception) {
            null

        }?.let {
            for (i in 0 until it.length()) {
                with(it.getJSONObject(i)) {
                    if (!isNull("typeNotification")) {
                        listNotifications.add(
                            when (parseTypeMessage(getString("typeNotification"))) {
                                NEW_PUBLICATION -> createNewPublicationBo(this)
                                ERROR_TYPE -> Error
                            }
                        )
                    }
                }
            }
        }
        return listNotifications.filter {
            it.typeNotification != ERROR_TYPE
        }
    }

}