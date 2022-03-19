package es.gresybano.gresybano

import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import es.gresybano.gresybano.common.view.BaseActivity
import es.gresybano.gresybano.common.view.ToolbarGresYBano

@AndroidEntryPoint
class HostActivity : BaseActivity() {

    override var viewLoading: ConstraintLayout? = null
    override var lottieAnimation: LottieAnimationView? = null
    override var toolbar: ToolbarGresYBano? = null
    override var bottomNavigationBar: BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)
        initViews()
        configureAnimation()
        initToolbar()
        initBottomNavigationBar()
    }

    private fun initToolbar() {
        initToolbarActions(
            actionBack = { findNavController(R.id.activity_host__fragment_container__host).popBackStack() },
            actionClickScanQR = { /*TODO*/ },
            actionClickIconNotification = { /*TODO*/ },
            actionClickSearchView = { /*TODO*/ },
        )
    }

    private fun initBottomNavigationBar() {
        bottomNavigationBar?.setOnItemSelectedListener {
            when (it.itemId) {
                es.gresybano.gresybano.common.R.id.menu__activity_host__bottom_bar__navigation__favorite -> {
                    /*TODO*/
                    true
                }
                es.gresybano.gresybano.common.R.id.menu__activity_host__bottom_bar__navigation__home -> {
                    /*TODO*/
                    true
                }
                es.gresybano.gresybano.common.R.id.menu__activity_host__bottom_bar__navigation__settings -> {
                    /*TODO*/
                    true
                }
                else -> false
            }
        }
    }

    private fun initViews() {
        viewLoading = findViewById(R.id.activity_host__view__loading)
        lottieAnimation = findViewById(R.id.loading_screen__lottie_animation__loading)
        toolbar = findViewById(R.id.activity_host__toolbar__app)
        bottomNavigationBar = findViewById(R.id.activity_host__bottom_bar__navigation)
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