package es.gresybano.gresybano.feature.application.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import es.gresybano.gresybano.common.util.DEFAULT_ID_LONG
import es.gresybano.gresybano.common.view.BaseFragment
import es.gresybano.gresybano.feature.application.databinding.FragmentPublicationDetailsBinding
import es.gresybano.gresybano.feature.application.view.adapter.ImagePublicationPageAdapter
import es.gresybano.gresybano.feature.application.viewmodel.PublicationDetailsViewModel


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
        initListImages()
        initIdPublication()
        initIndicator()
        initImagesPager()
    }

    private fun initListImages() {
        viewModel.listImages = arguments?.getStringArray(KEY_LIST_IMAGES)?.toList() ?: listOf()
    }

    private fun initIdPublication() {
        viewModel.idPublication = arguments?.getLong(KEY_ID_PUBLICATION) ?: DEFAULT_ID_LONG
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