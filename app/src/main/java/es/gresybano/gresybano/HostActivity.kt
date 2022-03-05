package es.gresybano.gresybano

import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import dagger.hilt.android.AndroidEntryPoint
import es.gresybano.gresybano.common.view.BaseActivity

@AndroidEntryPoint
class HostActivity : BaseActivity() {

    override var viewLoading: ConstraintLayout? = null
    override var lottieAnimation: LottieAnimationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)
        viewLoading = findViewById(R.id.activity_host__view__loading)
        lottieAnimation = findViewById(R.id.loading_screen__lottie_animation__loading)
        configureAnimation()
    }

    private fun configureAnimation() {
        lottieAnimation?.let {
            with(it) {
                setAnimation(R.raw.animation_build_home)
                repeatCount = LottieDrawable.INFINITE
            }
        }
    }

}