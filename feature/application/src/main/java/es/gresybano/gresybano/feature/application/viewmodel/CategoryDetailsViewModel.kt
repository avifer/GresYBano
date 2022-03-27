package es.gresybano.gresybano.feature.application.viewmodel

import androidx.lifecycle.LiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import es.gresybano.gresybano.common.viewmodel.BaseViewModel
import es.gresybano.gresybano.common.viewmodel.defaultResponse
import es.gresybano.gresybano.domain.entities.PublicationBo
import es.gresybano.gresybano.feature.application.usecases.GetPublicationsOfCategoryUseCase
import javax.inject.Inject

@HiltViewModel
class CategoryDetailsViewModel @Inject constructor(
    private val getPublicationsOfCategoryUseCase: GetPublicationsOfCategoryUseCase
) : BaseViewModel() {

    fun getPublicationsOfCategory(idCategory: Long): LiveData<List<PublicationBo>?> {
        return defaultResponse { getPublicationsOfCategoryUseCase(idCategory) }
    }

}