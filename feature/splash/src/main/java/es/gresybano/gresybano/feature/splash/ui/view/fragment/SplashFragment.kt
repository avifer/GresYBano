package es.gresybano.gresybano.feature.splash.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import es.gresybano.gresybano.common.util.PreferencesUtil
import es.gresybano.gresybano.common.view.BaseFragment
import es.gresybano.gresybano.feature.splash.databinding.FragmentSplashBinding
import es.gresybano.gresybano.feature.splash.ui.viewmodel.SplashFragmentViewModel
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : BaseFragment() {

    companion object {
        private const val DELAY_SPLASH = 1500L
    }

    @Inject
    lateinit var preferencesUtil: PreferencesUtil

    override val viewModel by viewModels<SplashFragmentViewModel>()

    override fun getBindingCast() = binding as? FragmentSplashBinding

    override fun getInflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentSplashBinding.inflate(inflater, container, false)

    override fun onViewReady(savedInstanceState: Bundle?) {
        firstScreen()
        hideToolbar()
        hideBottomNavigationBar()
    }

    private fun firstScreen() {
        if (!preferencesUtil.getIsOnBoardingConfig()) {
            viewModel.getAllCategories()

        } else {
            viewModel.goToHome(DELAY_SPLASH)
        }
    }

}