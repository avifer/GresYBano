package es.gresybano.gresybano.messaging.extensions

import com.google.firebase.messaging.RemoteMessage
import es.gresybano.gresybano.domain.notification.entity.MessageNotificationBo
import es.gresybano.gresybano.messaging.entities.TypeMessageNotificationDto

fun RemoteMessage.toBo(): MessageNotificationBo =
    when (TypeMessageNotificationDto.parseTypeMessage(data["typeNotification"])) {
        TypeMessageNotificationDto.NEW_PUBLICATION -> MessageNotificationBo.createNewPublicationBo(data)
        TypeMessageNotificationDto.ERROR_TYPE -> MessageNotificationBo.Error
    }