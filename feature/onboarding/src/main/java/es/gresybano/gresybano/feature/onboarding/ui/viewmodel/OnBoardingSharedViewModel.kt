package es.gresybano.gresybano.feature.onboarding.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import es.gresybano.gresybano.common.util.parseJSON
import es.gresybano.gresybano.common.viewmodel.BaseViewModel
import es.gresybano.gresybano.domain.category.entity.CategoryBo

class OnBoardingSharedViewModel : BaseViewModel() {

    var listCategories: List<CategoryBo>? = null

    val categoriesFavoritesSelected = MutableLiveData<List<CategoryBo>>(listOf())

    fun saveListTopCategories(listInJson: String?) {
        listCategories = parseJSON(listInJson, Array<CategoryBo>::class.java)?.toList()
    }

}