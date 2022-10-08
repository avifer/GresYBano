package es.gresybano.gresybano.feature.splash.ui.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import es.gresybano.gresybano.common.util.ZERO_LONG
import es.gresybano.gresybano.common.util.parseToJSON
import es.gresybano.gresybano.common.util.runDelayMain
import es.gresybano.gresybano.common.viewmodel.BaseViewModel
import es.gresybano.gresybano.common.viewmodel.defaultResponse
import es.gresybano.gresybano.domain.category.entity.CategoryBo
import es.gresybano.gresybano.feature.splash.ui.view.fragment.SplashFragmentDirections
import es.gresybano.gresybano.feature.splash.usecases.GetAllCategoriesUseCase
import es.gresybano.gresybano.feature.splash.usecases.GetIfOnBoardingConfigUseCase
import javax.inject.Inject

@HiltViewModel
class SplashFragmentViewModel @Inject constructor(
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase,
    private val getIfOnBoardingConfigUseCase: GetIfOnBoardingConfigUseCase,
) : BaseViewModel() {

    companion object {
        private const val DELAY_SPLASH = 1500L
    }

    private fun goToOnBoarding(
        listCategories: List<CategoryBo>?,
        delay: Long = ZERO_LONG,
    ) {
        runDelayMain(delay) {
            navigate(
                SplashFragmentDirections.navigateFromSplashFeatureToOnboardingFeature(
                    parseToJSON(listCategories?.toTypedArray())
                )
            )
        }
    }

    private fun goToHome() {
        runDelayMain(DELAY_SPLASH) {
            navigate(SplashFragmentDirections.navigateFromSplashFeatureToApplicationFeature())
        }
    }

    private fun getAllCategories() {
        defaultResponse(getAllCategoriesUseCase(), false) {
            goToOnBoarding(it)
            it
        }
    }

    fun navigateToFirstScreen() {
        defaultResponse(getIfOnBoardingConfigUseCase(), false) {
            if (it == false) {
                getAllCategories()

            } else {
                goToHome()
            }
            it
        }
    }

}