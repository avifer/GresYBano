package es.gresybano.gresybano.feature.application.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import es.gresybano.gresybano.common.view.BaseFragment
import es.gresybano.gresybano.feature.application.databinding.FragmentPublicationDetailsBinding
import es.gresybano.gresybano.feature.application.view.adapter.ImagePublicationPageAdapter
import es.gresybano.gresybano.feature.application.viewmodel.PublicationDetailsViewModel


class PublicationDetailsFragment : BaseFragment() {

    companion object {
        private const val KEY_ID_PUBLICATION = "idPublication"
        private const val KEY_LIST_IMAGES = "listImages"
    }

    private lateinit var listImages: List<String>

    private val actionChangeImage =
        object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                getBindingCast()
                    ?.fragmentPublicationDetailsPageIndicatorIndicator
                    ?.changeSelectedIndicator(position)
            }
        }

    override val viewModel by viewModels<PublicationDetailsViewModel>()

    override fun getBindingCast() = binding as? FragmentPublicationDetailsBinding

    override fun getInflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentPublicationDetailsBinding.inflate(inflater, container, false)

    override fun onViewReady(savedInstanceState: Bundle?) {
        showToolbarGoBack()
        initListImages()
        initIndicator()
        initImagesPager()
    }

    private fun initListImages() {
        listImages = arguments?.getStringArray(KEY_LIST_IMAGES)?.toList() ?: listOf()
    }

    private fun initIndicator() {
        getBindingCast()?.fragmentPublicationDetailsPageIndicatorIndicator
            ?.setQuantityIndicator(listImages.size)
    }

    private fun initImagesPager() {
        getBindingCast()?.fragmentPublicationDetailsViewPagerImages?.let {
            with(it) {
                adapter = ImagePublicationPageAdapter(listImages)
                registerOnPageChangeCallback(actionChangeImage)
                offscreenPageLimit = listImages.size
            }
        }
    }

}