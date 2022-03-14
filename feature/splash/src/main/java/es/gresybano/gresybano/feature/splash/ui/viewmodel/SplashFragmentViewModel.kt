package es.gresybano.gresybano.feature.splash.ui.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import es.gresybano.gresybano.common.util.runDelayMain
import es.gresybano.gresybano.common.viewmodel.BaseViewModel
import es.gresybano.gresybano.common.viewmodel.executeWithListeners
import es.gresybano.gresybano.domain.category.usecases.GetAllCategoriesAndSaveLocal
import es.gresybano.gresybano.domain.entities.response.ExceptionInfo
import es.gresybano.gresybano.feature.splash.ui.view.fragment.SplashFragmentDirections
import javax.inject.Inject

@HiltViewModel
class SplashFragmentViewModel @Inject constructor(
    private val getAllCategoriesAndSaveLocal: GetAllCategoriesAndSaveLocal
) : BaseViewModel() {

    private fun goToOnBoarding(delay: Long = 0) {
        runDelayMain(delay) {
            navigate(SplashFragmentDirections.navigateFromSplashFeatureToOnboardingFeature())
        }
    }

    fun goToHome(delay: Long = 0) {
        runDelayMain(delay) {
            navigate(SplashFragmentDirections.navigateFromSplashFeatureToApplicationFeature())
        }
    }

    fun getAllCategories(
        error: suspend (error: ExceptionInfo) -> Unit,
    ) {
        executeWithListeners(
            successful = { goToOnBoarding() },
            error = error,
            loading = {},
        ) {
            getAllCategoriesAndSaveLocal()
        }

    }

}