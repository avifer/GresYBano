package es.gresybano.gresybano.feature.application.viewmodel

import androidx.lifecycle.LiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import es.gresybano.gresybano.common.usecase.GetAllNotificationsUseCase
import es.gresybano.gresybano.common.util.runInIO
import es.gresybano.gresybano.common.viewmodel.BaseViewModel
import es.gresybano.gresybano.common.viewmodel.defaultResponse
import es.gresybano.gresybano.common.viewmodel.postError
import es.gresybano.gresybano.common.viewmodel.postLoading
import es.gresybano.gresybano.domain.notification.entity.MessageNotificationBo
import es.gresybano.gresybano.domain.response.Response
import es.gresybano.gresybano.domain.response.getStringError
import es.gresybano.gresybano.feature.application.usecases.GetPublicationUseCase
import es.gresybano.gresybano.feature.application.usecases.MarkNotificationOpenedUseCase
import es.gresybano.gresybano.feature.application.view.fragment.NotificationsFragmentDirections
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel @Inject constructor(
    private val getDataHomeFragmentUseCase: GetAllNotificationsUseCase,
    private val markNotificationOpenedUseCase: MarkNotificationOpenedUseCase,
    private val getPublicationUseCase: GetPublicationUseCase,
) : BaseViewModel() {

    fun getAllNotifications(): LiveData<List<MessageNotificationBo>?> {
        return defaultResponse { getDataHomeFragmentUseCase() }
    }

    fun goToDetailPublication(publication: MessageNotificationBo.NewPublicationBo) {
        runInIO {
            getPublicationUseCase(publication.id).collect { response ->
                when (response) {
                    is Response.Error -> postError(response.error.getStringError())
                    is Response.Loading -> postLoading(true)
                    is Response.Successful -> {
                        markNotificationOpenedUseCase(publication.idNotification).collect { responseMarkOpened ->
                            @Suppress("NON_EXHAUSTIVE_WHEN_STATEMENT")
                            when (responseMarkOpened) {
                                is Response.Error -> postError(responseMarkOpened.error.getStringError())
                                is Response.Successful -> {
                                    response.data?.let {
                                        navigate(
                                            NotificationsFragmentDirections.navigateToNavigationFeatureApplicationPublicationDetailsFragmentFromNotificationFragment(
                                                idPublication = it.id,
                                                listImages = it.listImages.toTypedArray()
                                            )
                                        )

                                    } ?: kotlin.run { postDefaultError() }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}