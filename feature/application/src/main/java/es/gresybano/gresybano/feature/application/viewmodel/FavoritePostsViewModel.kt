package es.gresybano.gresybano.feature.application.viewmodel

import androidx.lifecycle.LiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import es.gresybano.gresybano.common.viewmodel.BaseViewModel
import es.gresybano.gresybano.common.viewmodel.defaultResponse
import es.gresybano.gresybano.domain.publication.entity.PublicationBo
import es.gresybano.gresybano.feature.application.usecases.GetPublicationsFavoritesUseCase
import es.gresybano.gresybano.feature.application.view.fragment.FavoritePublicationsFragmentDirections
import javax.inject.Inject

@HiltViewModel
class FavoritePostsViewModel @Inject constructor(
    private val getPublicationsFavoritesUseCase: GetPublicationsFavoritesUseCase
) : BaseViewModel() {

    fun getListFavorites(): LiveData<List<PublicationBo>?> =
        defaultResponse { getPublicationsFavoritesUseCase() }

    fun goToDetailCategory(idCategory: String, nameCategory: String, urlPrimary: String) {
        navigate(
            FavoritePublicationsFragmentDirections.navigateToNavigationFeatureApplicationCategoryDetailsFragment(
                idCategory = idCategory,
                nameCategory = nameCategory,
                primaryUrlCategory = urlPrimary,
                onlyFavorites = true
            )
        )
    }

    fun goToDetailPublication(idPublication: String, listImages: List<String>) {
        navigate(
            FavoritePublicationsFragmentDirections.navigateToNavigationFeatureApplicationPublicationDetailsFragment(
                idPublication = idPublication,
                listImages = listImages.toTypedArray()
            )
        )
    }

}