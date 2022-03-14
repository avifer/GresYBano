package es.gresybano.gresybano.feature.application.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import es.gresybano.gresybano.common.view.BaseFragment
import es.gresybano.gresybano.feature.application.databinding.FragmentSettingsBinding
import es.gresybano.gresybano.feature.application.viewmodel.SettingsViewModel

class SettingsFragment : BaseFragment() {

    override val viewModel by viewModels<SettingsViewModel>()

    override fun getBindingCast() = binding as? FragmentSettingsBinding

    override fun onViewReady(savedInstanceState: Bundle?) {
        showToolbarDefault()
        showBottomNavigationBar()
    }

    override fun getInflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentSettingsBinding.inflate(inflater, container, false)

}