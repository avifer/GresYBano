package es.gresybano.gresybano.feature.splash.ui.viewmodel

import es.gresybano.gresybano.common.util.runDelayMain
import es.gresybano.gresybano.common.viewmodel.BaseViewModel
import es.gresybano.gresybano.feature.splash.ui.view.fragment.SplashFragmentDirections

class SplashFragmentViewModel : BaseViewModel() {

    companion object {
        private const val DELAY_SPLASH = 1500L
    }

    fun goToOnBoarding() {
        runDelayMain(DELAY_SPLASH) {
            navigate(SplashFragmentDirections.navigateFromSplashFeatureToOnboardingFeature())
        }
    }

}