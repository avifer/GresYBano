package es.gresybano.gresybano.messaging

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.bumptech.glide.Glide
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import es.gresybano.gresybano.HostActivity
import es.gresybano.gresybano.common.usecase.SaveNotificationUseCase
import es.gresybano.gresybano.common.util.ZERO
import es.gresybano.gresybano.common.util.runInIO
import es.gresybano.gresybano.domain.notification.entity.MessageNotificationBo
import es.gresybano.gresybano.domain.notification.entity.getMainImage
import es.gresybano.gresybano.domain.response.Response
import es.gresybano.gresybano.messaging.extensions.toBo
import kotlinx.coroutines.flow.collect
import javax.inject.Inject
import kotlin.random.Random


@SuppressLint("MissingFirebaseInstanceTokenRefresh")
@AndroidEntryPoint
class FirebaseMessagingService : FirebaseMessagingService() {

    @Inject
    lateinit var saveNotificationUseCase: SaveNotificationUseCase

    companion object {
        const val KEY_INTENT_NOTIFICATION_RECEIVED = "NOTIFICATION_RECEIVED"
        private const val CHANNEL_ID_NOTIFICATION = "NOTIFICATION_GRESYBANO"
    }

    private fun createIntentNotification() =
        Intent(this, HostActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

    private fun pendingIntentHostActivity() =
        PendingIntent.getActivity(
            this,
            ZERO,
            createIntentNotification(),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

    private fun createChannelIfNecessary() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            getSystemService(NotificationManager::class.java)
                .createNotificationChannel(
                    NotificationChannel(
                        CHANNEL_ID_NOTIFICATION,
                        "Notification",
                        NotificationManager.IMPORTANCE_HIGH
                    )
                )
        }
    }

    private fun showNotification(title: String, subtitle: String, mainImage: String) {
        createChannelIfNecessary()
        val mainImageLoaded =
            try {
                Glide.with(this)
                    .asBitmap()
                    .load(mainImage)
                    .submit().get()
            } catch (e: Exception) {
                null
            }

        NotificationManagerCompat.from(this).apply {
            notify(
                Random.nextInt(),
                NotificationCompat
                    .Builder(this@FirebaseMessagingService, CHANNEL_ID_NOTIFICATION)
                    .apply {
                        setContentTitle(title)
                        setContentText(subtitle)
                        //TODO Cambiar este icono cuando tenga el definitivo
                        setSmallIcon(es.gresybano.gresybano.common.R.drawable.icon_home)
                        setAutoCancel(true)
                        mainImageLoaded?.let {
                            this.setLargeIcon(it)
                        }
                        setContentIntent(pendingIntentHostActivity())
                    }
                    .build()
            )
        }
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        with(remoteMessage.toBo()) {
            if (this !is MessageNotificationBo.Error) {
                runInIO {
                    saveNotificationUseCase(this).collect {
                        when (it) {
                            is Response.Successful ->
                                this@FirebaseMessagingService.sendBroadcast(
                                    Intent(KEY_INTENT_NOTIFICATION_RECEIVED)
                                )

                            is Response.Error,
                            is Response.Loading -> {
                                //no-op
                            }
                        }
                    }
                }
                showNotification(title, subtitle, getMainImage())
            }
        }
    }

}