package es.gresybano.gresybano.feature.application.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import es.gresybano.gresybano.common.view.BaseFragment
import es.gresybano.gresybano.feature.application.databinding.FragmentHomeBinding
import es.gresybano.gresybano.feature.application.view.adapter.HeightCategoryAdapter
import es.gresybano.gresybano.feature.application.view.adapter.HeightPublicationAdapter
import es.gresybano.gresybano.feature.application.viewmodel.HomeViewModel

class HomeFragment : BaseFragment() {

    override val viewModel by viewModels<HomeViewModel>()

    override fun getBindingCast() = binding as? FragmentHomeBinding

    private val adapterListCategories =
        HeightCategoryAdapter { /*TODO*/ }

    private val adapterListMorePopulars =
        HeightPublicationAdapter { /*TODO*/ }

    private val adapterListLastPublications =
        HeightPublicationAdapter { /*TODO*/ }

    private val actionSeeMoreCategories = { /*TODO*/ }

    private val actionSeeMoreMorePopulars = { /*TODO*/ }

    private val actionSeeMoreLastPublications = { /*TODO*/ }


    override fun onViewReady(savedInstanceState: Bundle?) {
        showToolbarDefault()
        showBottomNavigationBar()
        initList()
    }

    private fun initList() {
        getBindingCast()?.let {
            with(it) {
                initListCategories(adapterListCategories, actionSeeMoreCategories)
                initListLasPublished(adapterListMorePopulars, actionSeeMoreMorePopulars)
                initListPopulars(adapterListLastPublications, actionSeeMoreLastPublications)
            }
        }
    }

    override fun getInflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentHomeBinding.inflate(inflater, container, false)

    private fun FragmentHomeBinding.initListCategories(
        adapter: HeightCategoryAdapter,
        actionClickSeeMore: () -> Unit
    ) {
        with(fragmentHomeListCategories) {
            setAdapter(adapter)
            setOnClickSeeMore { actionClickSeeMore() }
        }
    }

    private fun FragmentHomeBinding.initListPopulars(
        adapter: HeightPublicationAdapter,
        actionClickSeeMore: () -> Unit
    ) {
        with(fragmentHomeListMorePopulars) {
            setAdapter(adapter)
            setOnClickSeeMore { actionClickSeeMore() }
        }
    }

    private fun FragmentHomeBinding.initListLasPublished(
        adapter: HeightPublicationAdapter,
        actionClickSeeMore: () -> Unit
    ) {
        with(fragmentHomeListLastPublished) {
            setAdapter(adapter)
            setOnClickSeeMore { actionClickSeeMore() }
        }
    }

}