package es.gresybano.gresybano.feature.application.viewmodel

import androidx.lifecycle.LiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import es.gresybano.gresybano.common.viewmodel.BaseViewModel
import es.gresybano.gresybano.common.viewmodel.defaultResponse
import es.gresybano.gresybano.domain.publication.entity.PublicationBo
import es.gresybano.gresybano.feature.application.usecases.GetPublicationsOfCategoryUseCase
import es.gresybano.gresybano.feature.application.view.fragment.CategoryDetailsFragmentDirections
import javax.inject.Inject

@HiltViewModel
class CategoryDetailsViewModel @Inject constructor(
    private val getPublicationsOfCategoryUseCase: GetPublicationsOfCategoryUseCase
) : BaseViewModel() {

    fun getPublicationsOfCategory(idCategory: Long?): LiveData<List<PublicationBo>?> {
        return defaultResponse { getPublicationsOfCategoryUseCase(idCategory) }
    }

    fun goToDetailPublication(idPublication: Long, listImages: List<String>) {
        navigate(
            CategoryDetailsFragmentDirections.navigateToNavigationFeatureApplicationPublicationDetailsFragment(
                idPublication = idPublication,
                listImages = listImages.toTypedArray()
            )
        )
    }

}