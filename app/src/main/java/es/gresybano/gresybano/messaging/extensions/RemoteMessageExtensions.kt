package es.gresybano.gresybano.messaging.extensions

import com.google.firebase.messaging.RemoteMessage
import es.gresybano.gresybano.domain.notification.entity.MessageNotificationBo
import es.gresybano.gresybano.domain.notification.entity.TypeMessageNotification

fun RemoteMessage.toBo(): MessageNotificationBo =
    when (TypeMessageNotification.parseTypeMessage(data["typeNotification"])) {
        TypeMessageNotification.NEW_PUBLICATION -> MessageNotificationBo.createNewPublicationBo(data)
        TypeMessageNotification.ERROR_TYPE -> MessageNotificationBo.Error
    }