package es.gresybano.gresybano.feature.application.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import es.gresybano.gresybano.common.view.BaseFragment
import es.gresybano.gresybano.feature.application.databinding.FragmentFavoritePublicationsBinding
import es.gresybano.gresybano.feature.application.view.adapter.FavoritePublicationsAdapter
import es.gresybano.gresybano.feature.application.viewmodel.FavoritePostsViewModel

@AndroidEntryPoint
class FavoritePublicationsFragment : BaseFragment() {

    override val viewModel by viewModels<FavoritePostsViewModel>()

    private val adapterFavoritePublications = FavoritePublicationsAdapter(
        {
            viewModel.goToDetailPublication(
                idPublication = it.id,
                listImages = it.listImages
            )
        },
        {
            //TODO Implementar
            Toast.makeText(requireContext(), it.nameCategory, Toast.LENGTH_SHORT).show()
        }
    )

    override fun getBindingCast() = binding as? FragmentFavoritePublicationsBinding

    override fun onViewReady(savedInstanceState: Bundle?) {
        showToolbarDefault()
        showBottomNavigationBar()
        initList()
        setElementInList()
    }

    private fun setElementInList() {
        viewModel.getListFavorites().observe(viewLifecycleOwner) {
            it?.let {
                adapterFavoritePublications.setElements(it)
                getBindingCast()?.listEmpty(it.isEmpty())

            } ?: kotlin.run { viewModel.postDefaultError() }
        }
    }

    override fun getInflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentFavoritePublicationsBinding.inflate(inflater, container, false)

    private fun initList() {
        getBindingCast()?.fragmentFavoritePublicationsListFavorites?.let {
            with(it) {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = adapterFavoritePublications
            }
        }
    }

    private fun FragmentFavoritePublicationsBinding.listEmpty(isEmpty: Boolean) {
        fragmentFavoritePublicationsLabelEmptyFavorite.isVisible = isEmpty
        fragmentFavoritePublicationsImgEmptyFavorite.isVisible = isEmpty
    }

}