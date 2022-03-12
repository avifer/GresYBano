package es.gresybano.gresybano.feature.onboarding.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import es.gresybano.gresybano.common.viewmodel.BaseViewModel
import es.gresybano.gresybano.domain.entities.category.CategoryBo

class OnBoardingSharedViewModel : BaseViewModel() {

    val categoriesFavoritesSelected = MutableLiveData<List<CategoryBo>>(listOf())

}