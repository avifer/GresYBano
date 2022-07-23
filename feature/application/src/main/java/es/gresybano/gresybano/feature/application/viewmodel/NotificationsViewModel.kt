package es.gresybano.gresybano.feature.application.viewmodel

import androidx.lifecycle.LiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import es.gresybano.gresybano.common.usecase.GetAllNotificationsUseCase
import es.gresybano.gresybano.common.viewmodel.BaseViewModel
import es.gresybano.gresybano.common.viewmodel.defaultResponse
import es.gresybano.gresybano.domain.notification.entity.MessageNotificationBo
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel @Inject constructor(
    private val getDataHomeFragmentUseCase: GetAllNotificationsUseCase
) : BaseViewModel() {

    fun getAllNotifications(): LiveData<List<MessageNotificationBo>?> {
        return defaultResponse { getDataHomeFragmentUseCase() }
    }

}