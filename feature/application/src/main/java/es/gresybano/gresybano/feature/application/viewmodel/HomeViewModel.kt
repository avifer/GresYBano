package es.gresybano.gresybano.feature.application.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import es.gresybano.gresybano.common.viewmodel.BaseViewModel
import es.gresybano.gresybano.common.viewmodel.executeWithListeners
import es.gresybano.gresybano.domain.entities.HomeListElementsVo
import es.gresybano.gresybano.domain.entities.response.ExceptionInfo
import es.gresybano.gresybano.feature.application.usecases.GetDataHomeFragmentUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getDataHomeFragmentUseCase: GetDataHomeFragmentUseCase
) : BaseViewModel() {

    private var dataSave: HomeListElementsVo? = null

    fun getDataSave() = dataSave

    fun getElementsHome(
        successful: suspend (successful: HomeListElementsVo?) -> Unit,
        error: suspend (error: ExceptionInfo) -> Unit,
        loading: suspend (loading: Boolean) -> Unit,
    ) = executeWithListeners(
        successful = { successful(it); dataSave = it },
        error = { error(it) },
        loading = { loading(it) },
        block = { getDataHomeFragmentUseCase() }
    )

}