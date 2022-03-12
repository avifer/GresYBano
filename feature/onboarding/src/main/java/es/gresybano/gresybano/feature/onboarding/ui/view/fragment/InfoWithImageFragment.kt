package es.gresybano.gresybano.feature.onboarding.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RawRes
import androidx.fragment.app.viewModels
import es.gresybano.gresybano.common.view.BaseFragment
import es.gresybano.gresybano.feature.onboarding.R
import es.gresybano.gresybano.feature.onboarding.databinding.FragmentInfoWithImageBinding
import es.gresybano.gresybano.feature.onboarding.ui.viewmodel.InfoWithImageViewModel

class InfoWithImageFragment : BaseFragment() {

    companion object {
        fun newInstance() = InfoWithImageFragment()
    }

    override val viewModel by viewModels<InfoWithImageViewModel>()

    override fun getBindingCast() = binding as? FragmentInfoWithImageBinding

    override fun onViewReady(savedInstanceState: Bundle?) {
        getBindingCast()?.initAnimation(R.raw.animation_info_with_image_fragment)
    }

    private fun FragmentInfoWithImageBinding.initAnimation(@RawRes animation: Int) {
        with(fragmentInfoWithImageLottieAnimation) {
            setAnimation(animation)
            playAnimation()
        }
    }

    override fun getInflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentInfoWithImageBinding.inflate(inflater, container, false)

}