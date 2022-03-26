package es.gresybano.gresybano.feature.application.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RawRes
import androidx.fragment.app.activityViewModels
import com.airbnb.lottie.LottieDrawable
import dagger.hilt.android.AndroidEntryPoint
import es.avifer.listheaderseemore.ListHeaderSeeMore
import es.avifer.listheaderseemore.ListHeaderSeeMoreAdapter
import es.gresybano.gresybano.common.extensions.invisible
import es.gresybano.gresybano.common.extensions.show
import es.gresybano.gresybano.common.extensions.visible
import es.gresybano.gresybano.common.view.BaseFragment
import es.gresybano.gresybano.common.view.toast
import es.gresybano.gresybano.domain.entities.HomeListElementsVo
import es.gresybano.gresybano.domain.entities.response.getStringError
import es.gresybano.gresybano.feature.application.databinding.FragmentHomeBinding
import es.gresybano.gresybano.feature.application.view.adapter.HeightCategoryAdapter
import es.gresybano.gresybano.feature.application.view.adapter.HeightPublicationAdapter
import es.gresybano.gresybano.feature.application.viewmodel.HomeViewModel

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    override val viewModel by activityViewModels<HomeViewModel>()

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
        loadDataLists()
    }

    private fun loadDataLists() {
        if (viewModel.getDataSave() != null) {
            viewModel.getDataSave()?.let { setDataInLists(it) }

        } else {
            viewModel.getElementsHome(
                { it?.let { setDataInLists(it) } },
                {
                    getBindingCast()?.showAnimation(es.gresybano.gresybano.common.R.raw.animation_error)
                    toast(it.getStringError())
                },
                {
                    if (it) {
                        getBindingCast()?.showAnimation(es.gresybano.gresybano.common.R.raw.animation_loading)
                    }
                }
            )
        }
    }

    private fun initList() {
        getBindingCast()?.let {
            with(it) {
                fragmentHomeListCategories.initListSeeMore(
                    adapterListCategories,
                    actionSeeMoreCategories
                )
                fragmentHomeListMorePopulars.initListSeeMore(
                    adapterListMorePopulars,
                    actionSeeMoreMorePopulars
                )
                fragmentHomeListLastPublished.initListSeeMore(
                    adapterListLastPublications,
                    actionSeeMoreLastPublications
                )
            }
        }
    }

    override fun getInflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentHomeBinding.inflate(inflater, container, false)

    private fun setDataInLists(homeListElementsVo: HomeListElementsVo) {
        getBindingCast()?.let {
            with(it) {
                fragmentHomeListCategories.visible(!homeListElementsVo.listCategories.isNullOrEmpty())
                fragmentHomeListMorePopulars.visible(!homeListElementsVo.listMorePopularPublications.isNullOrEmpty())
                fragmentHomeListLastPublished.visible(!homeListElementsVo.listLastPublished.isNullOrEmpty())
                fragmentHomeLottieChargeError.invisible()
            }
        }
        adapterListCategories.submitList(homeListElementsVo.listCategories)
        adapterListMorePopulars.submitList(homeListElementsVo.listMorePopularPublications)
        adapterListLastPublications.submitList(homeListElementsVo.listLastPublished)
    }

    private fun FragmentHomeBinding.showAnimation(@RawRes idAnimation: Int) {
        hideLists()
        with(fragmentHomeLottieChargeError) {
            show()
            setAnimation(idAnimation)
            repeatCount = LottieDrawable.INFINITE
            playAnimation()
        }
    }

    private fun FragmentHomeBinding.hideLists() {
        fragmentHomeListCategories.invisible()
        fragmentHomeListMorePopulars.invisible()
        fragmentHomeListLastPublished.invisible()
    }

    private fun <T> ListHeaderSeeMore.initListSeeMore(
        adapter: ListHeaderSeeMoreAdapter<T>,
        actionClickSeeMore: () -> Unit
    ) {
        setAdapter(adapter)
        setOnClickSeeMore { actionClickSeeMore() }
    }

}