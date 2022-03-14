package es.gresybano.gresybano.feature.application.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import es.gresybano.gresybano.common.view.BaseFragment
import es.gresybano.gresybano.feature.application.databinding.FragmentHomeBinding
import es.gresybano.gresybano.feature.application.viewmodel.HomeViewModel

class HomeFragment : BaseFragment() {

    override val viewModel by viewModels<HomeViewModel>()

    override fun getBindingCast() = binding as FragmentHomeBinding

    override fun onViewReady(savedInstanceState: Bundle?) {
        showToolbarDefault()
        showBottomNavigationBar()
    }

    override fun getInflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentHomeBinding.inflate(inflater, container, false)

}