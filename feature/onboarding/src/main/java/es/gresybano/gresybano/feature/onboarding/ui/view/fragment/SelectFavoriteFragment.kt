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
import es.gresybano.gresybano.feature.onboarding.databinding.FragmentSelectFavoriteBinding
import es.gresybano.gresybano.feature.onboarding.ui.view.adapter.FavoriteCategoriesAdapter
import es.gresybano.gresybano.feature.onboarding.ui.view.vo.FavoriteCategoryVo
import es.gresybano.gresybano.feature.onboarding.ui.view.vo.toBo
import es.gresybano.gresybano.feature.onboarding.ui.view.vo.toVo
import es.gresybano.gresybano.feature.onboarding.ui.viewmodel.OnBoardingSharedViewModel
import es.gresybano.gresybano.feature.onboarding.ui.viewmodel.SelectFavoriteViewModel

@AndroidEntryPoint
class SelectFavoriteFragment : BaseFragment() {

    companion object {
        fun newInstance() = SelectFavoriteFragment()

        private const val SPAN_COUNT_LIST = 2
    }

    override val viewModel by viewModels<SelectFavoriteViewModel>()

    private val viewModelShared by activityViewModels<OnBoardingSharedViewModel>()

    override fun getBindingCast() = binding as? FragmentSelectFavoriteBinding

    override fun onViewReady(savedInstanceState: Bundle?) {
        getBindingCast()?.initList()
        getFavorites()
    }

    private fun FragmentSelectFavoriteBinding.initList() {
        with(fragmentSelectFavoriteListFavorites) {
            layoutManager = GridLayoutManager(requireContext(), SPAN_COUNT_LIST)
            adapter = FavoriteCategoriesAdapter {
                viewModelShared.categoriesFavoritesSelected.postValue(getElements())
            }
        }
    }

    private fun getFavorites() {
        viewModel.getAllCategories(
            { listCategories ->
                getBindingCast()?.let {
                    with(it) {
                        listCategories?.let { listCategoriesNotNull ->
                            setElements(listCategoriesNotNull.map { it.toVo() })
                        }
                        stopAnimation()
                    }
                }
            },
            {
                getBindingCast()?.playAnimation(es.gresybano.gresybano.common.R.raw.animation_error)
                toast(R.string.error_chargin_categories)
            },
            { loading ->
                getBindingCast()?.let {
                    with(it) {
                        if (loading) {
                            playAnimation(es.gresybano.gresybano.common.R.raw.animation_loading)

                        } else {
                            stopAnimation()
                        }
                    }
                }
            }
        )
    }

    override fun getInflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentSelectFavoriteBinding.inflate(inflater, container, false)

    private fun FragmentSelectFavoriteBinding.setElements(listElements: List<FavoriteCategoryVo>) {
        (fragmentSelectFavoriteListFavorites.adapter as? FavoriteCategoriesAdapter)
            ?.submitList(listElements)
    }

    private fun FragmentSelectFavoriteBinding.getElements(): List<CategoryBo> {
        return (fragmentSelectFavoriteListFavorites.adapter as? FavoriteCategoriesAdapter)
            ?.currentList?.map { it.toBo() } ?: listOf()
    }

    private fun FragmentSelectFavoriteBinding.stopAnimation() {
        with(fragmentSelectFavoriteLottieAnimation) {
            pauseAnimation()
            hide()
        }
    }

    private fun FragmentSelectFavoriteBinding.playAnimation(@RawRes animation: Int) {
        with(fragmentSelectFavoriteLottieAnimation) {
            setAnimation(animation)
            repeatCount = LottieDrawable.INFINITE
            playAnimation()
            show()
        }
    }

}