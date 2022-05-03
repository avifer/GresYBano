package es.gresybano.gresybano.feature.application.viewmodel

import androidx.lifecycle.LiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import es.gresybano.gresybano.common.viewmodel.BaseViewModel
import es.gresybano.gresybano.common.viewmodel.defaultResponse
import es.gresybano.gresybano.domain.entities.HomeListElementsVo
import es.gresybano.gresybano.feature.application.usecases.GetDataHomeFragmentUseCase
import es.gresybano.gresybano.feature.application.view.fragment.HomeFragmentDirections
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getDataHomeFragmentUseCase: GetDataHomeFragmentUseCase
) : BaseViewModel() {

    private var dataSave: HomeListElementsVo? = null

    fun getDataSave() = dataSave

    fun saveData(homeListElementsVo: HomeListElementsVo?) {
        dataSave = homeListElementsVo
    }

    fun getElementsHome(): LiveData<HomeListElementsVo?> {
        return defaultResponse { getDataHomeFragmentUseCase() }
    }

    fun goToDetailCategory(idCategory: Long, nameCategory: String, urlPrimary: String) {
        navigate(
            HomeFragmentDirections.navigateToNavigationFeatureApplicationCategoryDetailsFragment(
                idCategory = idCategory,
                nameCategory = nameCategory,
                primaryUrlCategory = urlPrimary,
            )
        )
    }

    fun goToDetailPublication(idPublication: Long, listImages: List<String>) {
        navigate(
            HomeFragmentDirections.navigateToNavigationFeatureApplicationPublicationDetailsFragment(
                idPublication = idPublication,
                listImages = listImages.toTypedArray()
            )
        )
    }

}