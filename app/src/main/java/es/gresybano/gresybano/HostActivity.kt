package es.gresybano.gresybano

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import es.gresybano.gresybano.common.view.BaseActivity
import es.gresybano.gresybano.common.view.ToolbarGresYBano
import es.gresybano.gresybano.messaging.FirebaseMessagingService.Companion.KEY_INTENT_NOTIFICATION_RECEIVED

@AndroidEntryPoint
class HostActivity : BaseActivity() {

    override var viewLoading: ConstraintLayout? = null
    override var lottieAnimation: LottieAnimationView? = null
    override var toolbar: ToolbarGresYBano? = null
    override var bottomNavigationBar: BottomNavigationView? = null

    private val viewModel by viewModels<HostViewModel>()

    override fun newPublicationLiveData() = viewModel.newPublicationLiveData()

    private val receiverNotificationReceived: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            viewModel.getNotificationsAndPutInView { setToolbarAmountNotifications(it) }
            viewModel.postNewNotification()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)
        //TODO CONTROLAR SI LA APP ESTA ABIERTA, NO ABIRILA DE NUEVO Y SOLAMENTE LLEVAR A DONDE HAYA QUE LLEVAR
        initViews()
        configureAnimation()
        initToolbar()
        initBottomNavigationBar()
        viewModel.start()
        viewModel.getNotificationsAndPutInView { setToolbarAmountNotifications(it) }
    }

    private fun initToolbar() {
        initToolbarActions(
            actionBack = { findNavController(R.id.activity_host__fragment_container__host).popBackStack() },
            actionClickScanQR = { /*TODO*/ },
            actionClickIconNotification = {
                navigateActivity(es.gresybano.gresybano.feature.application.R.id.navigate_to_navigation__feature_application__notifications_fragment)
            },
            actionClickSearchView = { /*TODO*/ },
        )
    }

    private fun initBottomNavigationBar() {
        val navigationFavoritePosts =
            es.gresybano.gresybano.feature.application.R.id.navigate_to_navigation__feature_application__favorite_posts_fragment
        val navigationHome =
            es.gresybano.gresybano.feature.application.R.id.navigate_to_navigation__feature_application__home_fragment
        val navigationSettings =
            es.gresybano.gresybano.feature.application.R.id.navigate_to_navigation__feature_application__settings_fragment

        val clickOnElements: (menuItem: MenuItem) -> Boolean =
            { menuItem ->
                when (menuItem.itemId) {
                    es.gresybano.gresybano.common.R.id.menu__activity_host__bottom_bar__navigation__favorite_posts -> {
                        navigateActivity(navigationFavoritePosts)
                        true
                    }
                    es.gresybano.gresybano.common.R.id.menu__activity_host__bottom_bar__navigation__home -> {
                        navigateActivity(navigationHome)
                        true
                    }
                    es.gresybano.gresybano.common.R.id.menu__activity_host__bottom_bar__navigation__settings -> {
                        navigateActivity(navigationSettings)
                        true
                    }
                    else -> false
                }
            }

        bottomNavigationBar?.let { bottomNav ->
            with(bottomNav) {
                selectedItemId =
                    es.gresybano.gresybano.common.R.id.menu__activity_host__bottom_bar__navigation__home
                setOnItemSelectedListener { clickOnElements(it) }
                setOnItemReselectedListener { clickOnElements(it) }
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
                setAnimation(es.gresybano.gresybano.common.R.raw.animation_loading)
                repeatCount = LottieDrawable.INFINITE
            }
        }
    }

    private fun navigateActivity(navigation: Int) {
        findNavController(R.id.activity_host__fragment_container__host).navigate(navigation)
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(
            receiverNotificationReceived,
            IntentFilter(KEY_INTENT_NOTIFICATION_RECEIVED)
        )
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiverNotificationReceived)
    }

}