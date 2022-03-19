package es.gresybano.gresybano.feature.onboarding.ui.view.fragment

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import dagger.hilt.android.AndroidEntryPoint
import es.gresybano.gresybano.common.extensions.getColorState
import es.gresybano.gresybano.common.extensions.hide
import es.gresybano.gresybano.common.extensions.show
import es.gresybano.gresybano.common.util.PreferencesUtil
import es.gresybano.gresybano.common.view.BaseFragment
import es.gresybano.gresybano.feature.onboarding.databinding.FragmentOnBoardingBinding
import es.gresybano.gresybano.feature.onboarding.ui.view.adapter.OnBoardingViewPagerAdapter
import es.gresybano.gresybano.feature.onboarding.ui.view.adapter.OnBoardingViewPagerAdapter.Companion.FIRST_PAGE
import es.gresybano.gresybano.feature.onboarding.ui.view.adapter.OnBoardingViewPagerAdapter.Companion.LAST_PAGE
import es.gresybano.gresybano.feature.onboarding.ui.view.adapter.OnBoardingViewPagerAdapter.Companion.TOTAL_FRAGMENTS
import es.gresybano.gresybano.feature.onboarding.ui.viewmodel.OnBoardingSharedViewModel
import es.gresybano.gresybano.feature.onboarding.ui.viewmodel.OnBoardingViewModel
import javax.inject.Inject

@AndroidEntryPoint
class OnBoardingFragment : BaseFragment() {

    override val viewModel by viewModels<OnBoardingViewModel>()

    private val viewModelShared by activityViewModels<OnBoardingSharedViewModel>()

    @Inject
    lateinit var preferencesUtil: PreferencesUtil

    override fun getBindingCast() = binding as? FragmentOnBoardingBinding

    override fun onViewReady(savedInstanceState: Bundle?) {
        getBindingCast()?.initActionButtons()
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

    private fun FragmentOnBoardingBinding.firstPage() {
        fragmentOnBoardingImgGoBack.hide()
    }

    private fun FragmentOnBoardingBinding.lastPage() {
        fragmentOnBoardingImgGoBack.show()
    }

    private fun FragmentOnBoardingBinding.betweenPage() {
        fragmentOnBoardingImgGoBack.show()
    }

    //TODO Remove this and change for custom library
    private fun updateIndicatorPage(position: Int) {
        getBindingCast()?.fragmentOnBoardingLinearIndicator?.let {
            with(it) {
                removeAllViews()
                val dots = MutableList(TOTAL_FRAGMENTS) { TextView(requireContext()) }
                dots.forEach { itextView ->
                    addView(itextView)
                    with(itextView) {
                        text = Html.fromHtml("&#8226")
                        textSize = 35F
                        setTextColor(requireContext().getColorState(es.gresybano.gresybano.common.R.color.primary_color))
                    }
                }
                dots[position].setTextColor(requireContext().getColorState(es.gresybano.gresybano.common.R.color.primary_color_dark))
            }
        }
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

    private fun FragmentOnBoardingBinding.initActionButtons() {
        fragmentOnBoardingImgGoBack.setOnClickListener { fragmentOnBoardingViewPagerFragments.currentItem-- }

        fragmentOnBoardingImgGoForward.setOnClickListener {
            if (fragmentOnBoardingViewPagerFragments.currentItem == LAST_PAGE) {
                viewModelShared.categoriesFavoritesSelected.value?.let {
                    viewModel.saveCategories(it).observe(viewLifecycleOwner) {
                        preferencesUtil.setIsOnBoardingConfig()
                        viewModel.navigateToHome()
                    }
                }

            } else {
                fragmentOnBoardingViewPagerFragments.currentItem++
            }
        }
    }

    override fun getInflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentOnBoardingBinding.inflate(inflater, container, false)

}