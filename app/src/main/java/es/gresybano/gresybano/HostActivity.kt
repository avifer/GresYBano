package es.gresybano.gresybano

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.MenuItem
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import es.gresybano.gresybano.common.usecase.GetAllNotificationsUseCase
import es.gresybano.gresybano.common.util.runInIO
import es.gresybano.gresybano.common.util.runInMain
import es.gresybano.gresybano.common.view.BaseActivity
import es.gresybano.gresybano.common.view.ToolbarGresYBano
import es.gresybano.gresybano.domain.response.Response
import es.gresybano.gresybano.messaging.FirebaseMessagingService.Companion.KEY_INTENT_NOTIFICATION_RECEIVED
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class HostActivity : BaseActivity() {

    override var viewLoading: ConstraintLayout? = null
    override var lottieAnimation: LottieAnimationView? = null
    override var toolbar: ToolbarGresYBano? = null
    override var bottomNavigationBar: BottomNavigationView? = null

    //TODO Revisar implementacion. Pasar logica a viewModel

    @Inject
    lateinit var getAllNotificationsUseCase: GetAllNotificationsUseCase

    private val receiverNotificationReceived: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            getNotificationsAndPutInView()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)
        //TODO CONTROLAR SI LA APP ESTA ABIERTA, NO ABIRILA DE NUEVO Y SOLAMENTE LLEVAR A DONDE HAYA QUE LLEVAR
        FirebaseMessaging.getInstance()
            .subscribeToTopic("all")     //TODO Eliminar cuando se acabe el desarrollo
        initViews()
        configureAnimation()
        initToolbar()
        initBottomNavigationBar()
        getNotificationsAndPutInView()
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
                        findNavController(R.id.activity_host__fragment_container__host).navigate(
                            navigationFavoritePosts
                        )
                        true
                    }
                    es.gresybano.gresybano.common.R.id.menu__activity_host__bottom_bar__navigation__home -> {
                        findNavController(R.id.activity_host__fragment_container__host).navigate(
                            navigationHome
                        )
                        true
                    }
                    es.gresybano.gresybano.common.R.id.menu__activity_host__bottom_bar__navigation__settings -> {
                        findNavController(R.id.activity_host__fragment_container__host).navigate(
                            navigationSettings
                        )
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

    private fun getNotificationsAndPutInView() {
        runInIO {
            getAllNotificationsUseCase().collect { response ->
                when (response) {
                    is Response.Successful -> {
                        response.data?.let {
                            runInMain {
                                setToolbarAmountNotifications(it.size)
                            }
                        }
                    }
                    is Response.Error,
                    is Response.Loading -> {
                        //no-op
                    }
                }
            }
        }
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