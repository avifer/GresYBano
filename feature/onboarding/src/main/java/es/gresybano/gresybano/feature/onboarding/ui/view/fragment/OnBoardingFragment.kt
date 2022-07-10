package es.gresybano.gresybano.feature.onboarding.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import dagger.hilt.android.AndroidEntryPoint
import es.gresybano.gresybano.common.extensions.hide
import es.gresybano.gresybano.common.extensions.show
import es.gresybano.gresybano.common.view.BaseFragment
import es.gresybano.gresybano.feature.onboarding.databinding.FragmentOnBoardingBinding
import es.gresybano.gresybano.feature.onboarding.ui.view.adapter.OnBoardingViewPagerAdapter
import es.gresybano.gresybano.feature.onboarding.ui.view.adapter.OnBoardingViewPagerAdapter.Companion.FIRST_PAGE
import es.gresybano.gresybano.feature.onboarding.ui.view.adapter.OnBoardingViewPagerAdapter.Companion.LAST_PAGE
import es.gresybano.gresybano.feature.onboarding.ui.view.adapter.OnBoardingViewPagerAdapter.Companion.TOTAL_FRAGMENTS
import es.gresybano.gresybano.feature.onboarding.ui.viewmodel.OnBoardingSharedViewModel
import es.gresybano.gresybano.feature.onboarding.ui.viewmodel.OnBoardingViewModel

@AndroidEntryPoint
class OnBoardingFragment : BaseFragment() {

    companion object {
        private const val KEY_LIST_CATEGORIES = "listCategories"
    }

    override val viewModel by viewModels<OnBoardingViewModel>()

    private val viewModelShared by activityViewModels<OnBoardingSharedViewModel>()

    override fun getBindingCast() = binding as? FragmentOnBoardingBinding

    private val actionSaveFavorites = {
        with(viewModel) {
            viewModelShared.categoriesFavoritesSelected.value?.let {
                saveFavoriteCategories(it)

            } ?: kotlin.run { postDefaultError() }
        }
    }

    override fun onViewReady(savedInstanceState: Bundle?) {
        getListTopCategories()
        initPageIndicator()
        getBindingCast()?.initActionButtons(actionSaveFavorites)
        initViewPager()
        hideToolbar()
        hideBottomNavigationBar()
    }

    private fun initViewPager() {
        getBindingCast()?.fragmentOnBoardingViewPagerFragments?.let {
            with(it) {
                adapter = OnBoardingViewPagerAdapter(requireParentFragment())
                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        updateIndicatorPage(position)
                        updateButtonsPage(position)
                    }
                })
                offscreenPageLimit = 3
            }
        }
    }

    private fun initPageIndicator() {
        getBindingCast()?.fragmentOnBoardingPageIndicatorIndicator
            ?.setQuantityIndicator(TOTAL_FRAGMENTS, FIRST_PAGE)
    }

    private fun updateIndicatorPage(position: Int) {
        getBindingCast()?.fragmentOnBoardingPageIndicatorIndicator?.changeSelectedIndicator(position)
    }

    private fun updateButtonsPage(position: Int) {
        when (position) {
            FIRST_PAGE -> {
                getBindingCast()?.firstPage()
            }
            LAST_PAGE -> {
                getBindingCast()?.lastPage()
            }
            else -> {
                getBindingCast()?.betweenPage()
            }
        }
    }

    private fun getListTopCategories() {
        viewModelShared.saveListCategories(arguments?.getString(KEY_LIST_CATEGORIES))
    }

    private fun FragmentOnBoardingBinding.firstPage() {
        fragmentOnBoardingImgGoBack.hide()
    }

    private fun FragmentOnBoardingBinding.lastPage() {
        fragmentOnBoardingImgGoBack.show()
    }

    private fun FragmentOnBoardingBinding.betweenPage() {
        fragmentOnBoardingImgGoBack.show()
    }

    private fun FragmentOnBoardingBinding.initActionButtons(actionSaveFavorites: () -> Unit) {
        fragmentOnBoardingImgGoBack.setOnClickListener { fragmentOnBoardingViewPagerFragments.currentItem-- }
        fragmentOnBoardingImgGoForward.setOnClickListener {
            if (fragmentOnBoardingViewPagerFragments.currentItem == LAST_PAGE) {
                actionSaveFavorites()

            } else {
                fragmentOnBoardingViewPagerFragments.currentItem++
            }
        }
    }

    override fun getInflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentOnBoardingBinding.inflate(inflater, container, false)

}