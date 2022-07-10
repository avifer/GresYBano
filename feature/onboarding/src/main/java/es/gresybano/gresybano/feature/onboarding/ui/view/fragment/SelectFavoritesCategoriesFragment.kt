package es.gresybano.gresybano.feature.onboarding.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RawRes
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.airbnb.lottie.LottieDrawable
import dagger.hilt.android.AndroidEntryPoint
import es.gresybano.gresybano.common.extensions.hide
import es.gresybano.gresybano.common.extensions.show
import es.gresybano.gresybano.common.view.BaseFragment
import es.gresybano.gresybano.common.view.toast
import es.gresybano.gresybano.domain.entities.CategoryBo
import es.gresybano.gresybano.feature.onboarding.R
import es.gresybano.gresybano.feature.onboarding.databinding.FragmentSelectFavoritesCategoriesBinding
import es.gresybano.gresybano.feature.onboarding.ui.view.adapter.FavoriteCategoriesAdapter
import es.gresybano.gresybano.feature.onboarding.ui.view.vo.FavoriteCategoryVo
import es.gresybano.gresybano.feature.onboarding.ui.view.vo.toBo
import es.gresybano.gresybano.feature.onboarding.ui.view.vo.toVo
import es.gresybano.gresybano.feature.onboarding.ui.viewmodel.OnBoardingSharedViewModel
import es.gresybano.gresybano.feature.onboarding.ui.viewmodel.SelectFavoriteViewModel

@AndroidEntryPoint
class SelectFavoritesCategoriesFragment : BaseFragment() {

    companion object {
        fun newInstance() = SelectFavoritesCategoriesFragment()

        private const val SPAN_COUNT_LIST = 2
    }

    override val viewModel by viewModels<SelectFavoriteViewModel>()

    private val viewModelShared by activityViewModels<OnBoardingSharedViewModel>()

    override fun getBindingCast() = binding as? FragmentSelectFavoritesCategoriesBinding

    override fun onViewReady(savedInstanceState: Bundle?) {
        getBindingCast()?.playAnimation(es.gresybano.gresybano.common.R.raw.animation_loading)
        getBindingCast()?.initList()
        getFavorites()
    }

    private fun FragmentSelectFavoritesCategoriesBinding.initList() {
        with(fragmentSelectFavoriteListFavorites) {
            layoutManager = GridLayoutManager(requireContext(), SPAN_COUNT_LIST)
            adapter = FavoriteCategoriesAdapter {
                viewModelShared.categoriesFavoritesSelected.postValue(getElements())
            }
        }
    }

    private fun getFavorites() {
        getBindingCast()?.let { bindingNotNull ->
            with(bindingNotNull) {
                stopAnimation()
                viewModelShared.listCategories?.let { listCategoriesNotNull ->
                    setElements(listCategoriesNotNull.map { it.toVo() })

                } ?: kotlin.run {
                    toast(R.string.error_chargin_categories)
                    playAnimation(es.gresybano.gresybano.common.R.raw.animation_error)
                }
            }
        }
    }

    override fun getInflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentSelectFavoritesCategoriesBinding.inflate(inflater, container, false)

    private fun FragmentSelectFavoritesCategoriesBinding.setElements(listElements: List<FavoriteCategoryVo>) {
        (fragmentSelectFavoriteListFavorites.adapter as? FavoriteCategoriesAdapter)
            ?.submitList(listElements)
    }

    private fun FragmentSelectFavoritesCategoriesBinding.getElements(): List<CategoryBo> {
        return (fragmentSelectFavoriteListFavorites.adapter as? FavoriteCategoriesAdapter)
            ?.currentList?.map { it.toBo() } ?: listOf()
    }

    private fun FragmentSelectFavoritesCategoriesBinding.stopAnimation() {
        with(fragmentSelectFavoriteLottieAnimation) {
            pauseAnimation()
            hide()
        }
    }

    private fun FragmentSelectFavoritesCategoriesBinding.playAnimation(@RawRes animation: Int) {
        with(fragmentSelectFavoriteLottieAnimation) {
            setAnimation(animation)
            repeatCount = LottieDrawable.INFINITE
            playAnimation()
            show()
        }
    }

}