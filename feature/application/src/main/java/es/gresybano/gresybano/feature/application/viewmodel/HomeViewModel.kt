package es.gresybano.gresybano.feature.application.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import es.gresybano.gresybano.common.viewmodel.BaseViewModel
import es.gresybano.gresybano.common.viewmodel.defaultResponse
import es.gresybano.gresybano.domain.entities.HomeListElementsVo
import es.gresybano.gresybano.feature.application.usecases.GetDataHomeFragmentUseCase
import es.gresybano.gresybano.feature.application.view.fragment.CategoryDetailsFragmentDirections
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

    fun getElementsHome() = defaultResponse { getDataHomeFragmentUseCase() }

    fun goToDetailCategory(idCategory: Long, nameCategory: String, urlPrimary: String) {
        navigate(
            CategoryDetailsFragmentDirections.navigateToNavigationFeatureApplicationCategoryDetailsFragment(
                idCategory, nameCategory, urlPrimary
            )
        )
    }

}