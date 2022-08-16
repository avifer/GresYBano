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
    private val getPublicationsOfCategoryUseCase: GetPublicationsOfCategoryUseCase,
) : BaseViewModel() {

    private var onlyFavorites = false

    fun saveOnlyFavorites(onlyFavorites: Boolean?) {
        this.onlyFavorites = onlyFavorites ?: false
    }

    fun getPublicationsOfCategory(idCategory: String?): LiveData<List<PublicationBo>?> {
        return defaultResponse(getPublicationsOfCategoryUseCase(idCategory)) { data ->
            if (onlyFavorites) {
                data?.filter { it.favorite }

            } else {
                data
            }
        }
    }

    fun goToDetailPublication(idPublication: String, listImages: List<String>) {
        navigate(
            CategoryDetailsFragmentDirections.navigateToNavigationFeatureApplicationPublicationDetailsFragment(
                idPublication = idPublication,
                listImages = listImages.toTypedArray()
            )
        )
    }

}