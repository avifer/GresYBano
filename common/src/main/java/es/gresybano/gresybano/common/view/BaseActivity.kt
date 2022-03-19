package es.gresybano.gresybano.common.view

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.bottomnavigation.BottomNavigationView
import es.gresybano.gresybano.common.extensions.hide
import es.gresybano.gresybano.common.extensions.invisible
import es.gresybano.gresybano.common.extensions.show
import es.gresybano.gresybano.common.view.BaseActivity.Companion.URL_INTENT_MARKET
import es.gresybano.gresybano.common.view.BaseActivity.Companion.URL_INTENT_PLAY_STORE

abstract class BaseActivity : AppCompatActivity() {

    companion object {
        const val URL_INTENT_PLAY_STORE = "https://play.google.com/store/apps/details?id="
        const val URL_INTENT_MARKET = "market://details?id="
    }

    val versionName: String by lazy {
        try {
            packageManager.getPackageInfo(packageName, 0).versionName
        } catch (e: Exception) {
            ""
        }
    }

    abstract var viewLoading: ConstraintLayout?

    abstract var lottieAnimation: LottieAnimationView?

    abstract var toolbar: ToolbarGresYBano?

    abstract var bottomNavigationBar: BottomNavigationView?

    private fun showLoading() {
        viewLoading?.show()
        lottieAnimation?.playAnimation()
    }

    private fun hideLoading() {
        viewLoading?.hide()
        lottieAnimation?.pauseAnimation()
    }

    fun visibilityLoading(visible: Boolean) {
        if (visible) {
            showLoading()

        } else {
            hideLoading()
        }
    }

    fun hideBottomNavigationBar() {
        bottomNavigationBar?.invisible()
    }

    fun showBottomNavigationBar() {
        bottomNavigationBar?.show()
    }

    fun hideToolbar() {
        toolbar?.invisible()
    }

    fun showToolbarDefault(amount: Int = 0) {
        toolbar?.showToolbarDefault(amount)
    }

    fun showToolbarGoBack() {
        toolbar?.showToolbarGoBack()
    }

    fun initToolbarActions(
        actionBack: () -> Unit,
        actionClickScanQR: () -> Unit,
        actionClickIconNotification: () -> Unit,
        actionClickSearchView: () -> Unit
    ) {
        toolbar?.initToolbarActions(
            actionBack,
            actionClickScanQR,
            actionClickIconNotification,
            actionClickSearchView
        )
    }

    fun setToolbarAmountNotifications(amount: Int) {
        toolbar?.setAmountNotifications(amount)
    }

}

fun BaseActivity.toast(
    @StringRes idString: Int,
    duration: Int = Toast.LENGTH_SHORT
) {
    Toast.makeText(
        this,
        getString(idString),
        duration
    ).show()
}

fun BaseActivity.openAppInGooglePlay() {
    try {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(URL_INTENT_MARKET + packageName)
            )
        )
    } catch (e: ActivityNotFoundException) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(URL_INTENT_PLAY_STORE + packageName)
            )
        )
    }
}