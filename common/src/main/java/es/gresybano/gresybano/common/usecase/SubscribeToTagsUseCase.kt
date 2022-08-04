package es.gresybano.gresybano.common.usecase

import com.google.firebase.messaging.FirebaseMessaging
import javax.inject.Inject

class SubscribeToTagsUseCase @Inject constructor() {

    operator fun invoke(vararg listTags: String) {
        listTags.forEach { FirebaseMessaging.getInstance().subscribeToTopic(it) }
    }

}