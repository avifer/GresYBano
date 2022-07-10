package es.gresybano.gresybano.feature.onboarding.ui.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.gresybano.gresybano.common.util.PreferencesUtil
import es.gresybano.gresybano.common.viewmodel.BaseViewModel
import es.gresybano.gresybano.domain.category.usecases.SaveCategoriesLocal
import es.gresybano.gresybano.domain.entities.CategoryBo
import es.gresybano.gresybano.feature.onboarding.ui.view.fragment.OnBoardingFragmentDirections
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val saveCategoriesLocal: SaveCategoriesLocal,
    private val preferencesUtil: PreferencesUtil
) : BaseViewModel() {

    fun saveFavoriteCategories(list: List<CategoryBo>) {
        viewModelScope.launch {
            saveCategoriesLocal(list).collect {
                onboardFinished()
                navigateToHome()
            }
        }
    }

    private fun navigateToHome() {
        navigate(OnBoardingFragmentDirections.navigateFromOnboardingFeatureToApplicationFeature())
    }

    private fun onboardFinished() {
        preferencesUtil.setIsOnBoardingConfig()
    }

}