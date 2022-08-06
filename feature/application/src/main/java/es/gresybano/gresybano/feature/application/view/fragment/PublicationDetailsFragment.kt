package es.gresybano.gresybano.feature.application.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import dagger.hilt.android.AndroidEntryPoint
import es.gresybano.gresybano.common.util.DEFAULT_ID_LONG
import es.gresybano.gresybano.common.view.BaseFragment
import es.gresybano.gresybano.feature.application.databinding.FragmentPublicationDetailsBinding
import es.gresybano.gresybano.feature.application.view.adapter.ImagePublicationPageAdapter
import es.gresybano.gresybano.feature.application.viewmodel.PublicationDetailsViewModel

@AndroidEntryPoint
class PublicationDetailsFragment : BaseFragment() {

    override val viewModel by viewModels<PublicationDetailsViewModel>()

    companion object {
        private const val KEY_ID_PUBLICATION = "idPublication"
        private const val KEY_LIST_IMAGES = "listImages"
    }

    private val actionChangeImage =
        object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                getBindingCast()
                    ?.fragmentPublicationDetailsPageIndicatorIndicator
                    ?.changeSelectedIndicator(position)
            }
        }

    override fun getBindingCast() = binding as? FragmentPublicationDetailsBinding

    override fun getInflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentPublicationDetailsBinding.inflate(inflater, container, false)

    override fun onViewReady(savedInstanceState: Bundle?) {
        showToolbarGoBack()
        getInfoPublication()
        initIndicator()
        initImagesPager()
        initIsFavorite()
        setClickFavorite()
    }

    private fun setClickFavorite() {
        getBindingCast()?.fragmentPublicationDetailsImgFavorite?.setOnClickListener {
            viewModel.addOrRemovePublicationToFavorite {
                setIconFavorite(it)
            }
        }
    }

    private fun initIsFavorite() {
        viewModel.getIsFavorite {
            setIconFavorite(it)
        }
    }

    private fun setIconFavorite(isFavorite: Boolean) {
        getBindingCast()?.fragmentPublicationDetailsImgFavorite?.setBackgroundResource(
            when (isFavorite) {
                true -> es.gresybano.gresybano.common.R.drawable.icon_favorite_selected
                false -> es.gresybano.gresybano.common.R.drawable.icon_favorite_unselected
            }
        )
    }

    private fun getInfoPublication() {
        viewModel.idPublication = arguments?.getLong(KEY_ID_PUBLICATION) ?: DEFAULT_ID_LONG
        viewModel.listImages = arguments?.getStringArray(KEY_LIST_IMAGES)?.toList() ?: listOf()
    }

    private fun initIndicator() {
        getBindingCast()?.fragmentPublicationDetailsPageIndicatorIndicator
            ?.setQuantityIndicator(viewModel.listImages.size)
    }

    private fun initImagesPager() {
        getBindingCast()?.fragmentPublicationDetailsViewPagerImages?.let {
            with(it) {
                adapter = ImagePublicationPageAdapter(viewModel.listImages)
                registerOnPageChangeCallback(actionChangeImage)
                offscreenPageLimit = viewModel.listImages.size
            }
        }
    }

}