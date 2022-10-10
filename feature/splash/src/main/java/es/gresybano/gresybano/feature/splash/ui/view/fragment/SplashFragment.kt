package es.gresybano.gresybano.feature.splash.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import es.gresybano.gresybano.common.view.BaseFragment
import es.gresybano.gresybano.common.view.getVersionName
import es.gresybano.gresybano.common.view.openAppInGooglePlay
import es.gresybano.gresybano.domain.splash.entity.VersionControlBo
import es.gresybano.gresybano.feature.splash.databinding.FragmentSplashBinding
import es.gresybano.gresybano.feature.splash.ui.view.custom.DialogVersionControl
import es.gresybano.gresybano.feature.splash.ui.viewmodel.SplashFragmentViewModel

@AndroidEntryPoint
class SplashFragment : BaseFragment() {

    private fun showPopUpControlVersion(versionControlBo: VersionControlBo) =
        DialogVersionControl(
            context = requireContext(),
            versionControlBo = versionControlBo,
            actionAccept = { openAppInGooglePlay() },
            actionCancel = { viewModel.navigateToFirstScreen() },
        ).show()

    override val viewModel by viewModels<SplashFragmentViewModel>()

    override fun getBindingCast() = binding as? FragmentSplashBinding

    override fun getInflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentSplashBinding.inflate(inflater, container, false)

    override fun onViewReady(savedInstanceState: Bundle?) {
        hideToolbar()
        hideBottomNavigationBar()
        initObservers()
        viewModel.checkVersionControl(getVersionName())
    }

    override fun onResume() {
        super.onResume()
        viewModel.getVersionControlGetter()?.let {
            showPopUpControlVersion(it)
        }
    }

    private fun initObservers() {
        viewModel.getShowPopUpLiveData().observe(viewLifecycleOwner) { showPopUpControlVersion(it) }
    }

}