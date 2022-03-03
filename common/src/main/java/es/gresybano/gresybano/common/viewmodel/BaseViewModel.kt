package es.gresybano.gresybano.common.viewmodel

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import es.gresybano.gresybano.common.util.runInIO
import es.gresybano.gresybano.common.util.runInMain
import es.gresybano.gresybano.domain.entities.response.ExceptionInfo
import es.gresybano.gresybano.domain.entities.response.Response
import es.gresybano.gresybano.domain.entities.response.getStringError
import es.gresybano.gresybano.navigation.Event
import es.gresybano.gresybano.navigation.Navigation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

open class BaseViewModel : ViewModel() {
    var defaultErrorNotification: LiveData<Int> = MutableLiveData()
    var defaultWaitingNotification: LiveData<Boolean> = MutableLiveData()

    private val navigation = MutableLiveData<Event<Navigation>>()

    fun getNavigation() = navigation as LiveData<Event<Navigation>>

    fun navigate(navDirections: NavDirections) {
        navigation.postValue(Event(Navigation.ToDirection(navDirections)))
    }

    fun navigateBack() {
        navigation.postValue(Event(Navigation.Back))
    }
}

fun <T> BaseViewModel.defaultResponse(
    block: suspend () -> Flow<Response<T>>
): MutableLiveData<T?> {
    val mutableLiveDataResult = MutableLiveData<T?>()
    runInIO {
        block().collect {
            when (it) {
                is Response.Error -> {
                    postError(it.getStringError())
                }
                is Response.Successful -> {
                    postSuccessful(it.data, mutableLiveDataResult)
                }
                is Response.Loading -> {
                    postLoading(true)
                }
            }
        }
    }
    return mutableLiveDataResult
}

fun <T> BaseViewModel.postSuccessful(data: T, mutableLiveDataResult: MutableLiveData<T?>) {
    mutableLiveDataResult.postValue(data)
    postLoading(false)
}

fun BaseViewModel.postError(@StringRes idError: Int) {
    (defaultErrorNotification as? MutableLiveData)?.postValue(idError)
    postLoading(false)
}

fun BaseViewModel.postLoading(loading: Boolean) {
    (defaultWaitingNotification as? MutableLiveData)?.postValue(loading)
}

fun <T> BaseViewModel.executeWithListeners(
    successful: suspend (successful: T?) -> Unit,
    error: suspend (error: ExceptionInfo) -> Unit,
    loading: suspend (loading: Boolean) -> Unit,
    enableDefaultLoading: Boolean = true,
    block: suspend () -> Flow<Response<T>>,
) {
    runInIO {
        block().collect {
            runInMain {
                when (it) {
                    is Response.Error -> {
                        error(it.error)
                        if (enableDefaultLoading) {
                            loading(false)
                        }
                    }
                    is Response.Successful -> {
                        successful(it.data)
                        if (enableDefaultLoading) {
                            loading(false)
                        }
                    }
                    is Response.Loading -> {
                        loading(it.loading)
                    }
                }
            }
        }
    }
}