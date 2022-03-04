package es.gresybano.gresybano.common.view

import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import es.gresybano.gresybano.common.extensions.hide
import es.gresybano.gresybano.common.extensions.show

abstract class BaseActivity : AppCompatActivity() {

    companion object {
        private const val INIT_PROGRESS_BAR = 0
    }

    val versionName: String by lazy {
        try {
            packageManager.getPackageInfo(packageName, 0).versionName
        } catch (e: Exception) {
            ""
        }
    }

    abstract var viewLoading: ConstraintLayout?
    abstract var progressBarLoading: ProgressBar?

    private fun showLoading() {
        viewLoading?.show()
        progressBarLoading?.startNestedScroll(INIT_PROGRESS_BAR)
    }

    private fun hideLoading() {
        viewLoading?.hide()
        progressBarLoading?.stopNestedScroll()
    }

    fun visibilityLoading(visible: Boolean) {
        if (visible) {
            showLoading()
        } else {
            hideLoading()
        }
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