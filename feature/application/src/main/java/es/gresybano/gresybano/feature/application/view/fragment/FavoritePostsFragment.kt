package es.gresybano.gresybano.feature.application.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import es.gresybano.gresybano.common.view.BaseFragment
import es.gresybano.gresybano.feature.application.databinding.FragmentFavoritePostsBinding
import es.gresybano.gresybano.feature.application.viewmodel.FavoritePostsViewModel

class FavoritePostsFragment : BaseFragment() {

    override val viewModel by viewModels<FavoritePostsViewModel>()

    override fun getBindingCast() = binding as? FragmentFavoritePostsBinding

    override fun onViewReady(savedInstanceState: Bundle?) {
        showToolbarDefault()
        showBottomNavigationBar()
    }

    override fun getInflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentFavoritePostsBinding.inflate(inflater, container, false)

}