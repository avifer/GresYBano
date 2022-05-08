package es.gresybano.gresybano.feature.splash.ui.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import es.gresybano.gresybano.common.util.ZERO_LONG
import es.gresybano.gresybano.common.util.parseToJSON
import es.gresybano.gresybano.common.util.runDelayMain
import es.gresybano.gresybano.common.viewmodel.BaseViewModel
import es.gresybano.gresybano.common.viewmodel.executeWithListeners
import es.gresybano.gresybano.domain.category.entity.CategoryBo
import es.gresybano.gresybano.domain.response.ExceptionInfo
import es.gresybano.gresybano.feature.splash.domain.GetAllCategoriesUseCase
import es.gresybano.gresybano.feature.splash.ui.view.fragment.SplashFragmentDirections
import javax.inject.Inject

@HiltViewModel
class SplashFragmentViewModel @Inject constructor(
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase
) : BaseViewModel() {

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

    fun goToHome(delay: Long = ZERO_LONG) {
        runDelayMain(delay) {
            navigate(SplashFragmentDirections.navigateFromSplashFeatureToApplicationFeature())
        }
    }

    fun getAllCategories(
        error: suspend (error: ExceptionInfo) -> Unit,
    ) {
        executeWithListeners(
            successful = { goToOnBoarding(it) },
            error = error,
            loading = {},
        ) {
            getAllCategoriesUseCase()
        }

    }

}