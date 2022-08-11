package es.gresybano.gresybano

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.lifecycle.HiltViewModel
import es.gresybano.gresybano.common.usecase.GetAllNotificationsUseCase
import es.gresybano.gresybano.common.util.runInMain
import es.gresybano.gresybano.common.viewmodel.BaseViewModel
import es.gresybano.gresybano.domain.response.Response
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HostViewModel @Inject constructor(
    private val getAllNotificationsUseCase: GetAllNotificationsUseCase
) : BaseViewModel() {

    private val newNotificationMutable = MutableLiveData<Boolean>()

    fun start() {
        if (BuildConfig.DEBUG) {
            FirebaseMessaging.getInstance().subscribeToTopic("debug")
        }
    }

    fun postNewNotification() {
        newNotificationMutable.postValue(true)
    }

    fun newPublicationLiveData() = newNotificationMutable as LiveData<Boolean>

    fun getNotificationsAndPutInView(actionUpdateView: (amountNotifications: Int) -> Unit) {
        viewModelScope.launch {
            getAllNotificationsUseCase().collect { response ->
                when (response) {
                    is Response.Successful -> {
                        response.data?.let {
                            runInMain {
                                actionUpdateView(it.filter { !it.isOpened }.size)
                            }
                        }
                    }
                    is Response.Error,
                    is Response.Loading -> {
                        //no-op
                    }
                }
            }
        }
    }

}