package es.gresybano.gresybano.feature.onboarding.ui.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.gresybano.gresybano.common.usecase.AddCategoriesToFavoriteUseCase
import es.gresybano.gresybano.common.usecase.SaveTagToFavoriteUseCase
import es.gresybano.gresybano.common.util.TAG_ALL_FIREBASE
import es.gresybano.gresybano.common.util.runInMain
import es.gresybano.gresybano.common.viewmodel.BaseViewModel
import es.gresybano.gresybano.domain.category.entity.CategoryBo
import es.gresybano.gresybano.feature.onboarding.ui.view.fragment.OnBoardingFragmentDirections
import es.gresybano.gresybano.feature.onboarding.usecases.SetOnBoardingConfigUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val saveCategoriesLocal: AddCategoriesToFavoriteUseCase,
    private val saveTagToFavoriteUseCase: SaveTagToFavoriteUseCase,
    private val setOnBoardingConfigUseCase: SetOnBoardingConfigUseCase
) : BaseViewModel() {

    fun saveFavoriteCategories(list: List<CategoryBo>) {
        viewModelScope.launch {
            saveTagToFavoriteUseCase(TAG_ALL_FIREBASE).collect { /*noop*/ }
            saveCategoriesLocal(list).collect {
                onboardFinished()
            }
        }
    }

    private fun navigateToHome() {
        navigate(OnBoardingFragmentDirections.navigateFromOnboardingFeatureToApplicationFeature())
    }

    private fun onboardFinished() {
        viewModelScope.launch {
            setOnBoardingConfigUseCase().collect {
                runInMain { navigateToHome() }
            }
        }
    }

}