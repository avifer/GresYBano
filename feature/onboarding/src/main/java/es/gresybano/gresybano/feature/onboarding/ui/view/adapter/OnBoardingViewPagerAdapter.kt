package es.gresybano.gresybano.feature.onboarding.ui.view.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import es.gresybano.gresybano.feature.onboarding.ui.view.fragment.InfoWithImageFragment
import es.gresybano.gresybano.feature.onboarding.ui.view.fragment.SelectFavoriteFragment

class OnBoardingViewPagerAdapter(fragmentParent: Fragment) : FragmentStateAdapter(fragmentParent) {

    companion object {
        const val TOTAL_FRAGMENTS = 2
        const val FIRST_PAGE = 0
        const val LAST_PAGE = TOTAL_FRAGMENTS - 1
    }

    override fun getItemCount(): Int = TOTAL_FRAGMENTS

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                InfoWithImageFragment.newInstance()
            }
            1 -> {
                SelectFavoriteFragment.newInstance()
            }
            else -> {
                InfoWithImageFragment.newInstance()
            }
        }
    }

}
