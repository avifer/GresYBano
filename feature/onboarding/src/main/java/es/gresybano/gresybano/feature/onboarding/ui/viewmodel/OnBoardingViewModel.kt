package es.gresybano.gresybano.feature.onboarding.ui.viewmodel

import androidx.lifecycle.LiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import es.gresybano.gresybano.common.viewmodel.BaseViewModel
import es.gresybano.gresybano.common.viewmodel.defaultResponse
import es.gresybano.gresybano.domain.category.usecases.SaveCategoriesLocal
import es.gresybano.gresybano.domain.entities.category.CategoryBo
import es.gresybano.gresybano.feature.onboarding.ui.view.fragment.OnBoardingFragmentDirections
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val saveCategoriesLocal: SaveCategoriesLocal
) : BaseViewModel() {

    fun saveCategories(list: List<CategoryBo>): LiveData<List<Long>?> {
        return defaultResponse { saveCategoriesLocal(list) }
    }

    fun navigateToHome() {
        navigate(OnBoardingFragmentDirections.navigateFromOnboardingFeatureToApplicationFeature())
    }

}