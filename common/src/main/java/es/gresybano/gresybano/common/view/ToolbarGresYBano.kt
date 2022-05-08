package es.gresybano.gresybano.common.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.text.isDigitsOnly
import es.gresybano.gresybano.common.R
import es.gresybano.gresybano.common.extensions.hide
import es.gresybano.gresybano.common.extensions.invisible
import es.gresybano.gresybano.common.extensions.show
import es.gresybano.gresybano.common.extensions.visible
import es.gresybano.gresybano.common.util.ZERO

class ToolbarGresYBano(context: Context, attributeSet: AttributeSet) :
    ConstraintLayout(context, attributeSet) {

    companion object {
        private const val MIN_AMOUNT_NOTIFICATION_SHOW = 0
        private const val MAX_AMOUNT_NOTIFICATION_SHOW = 9
        private const val STRING_MORE_THAN_MAX_NOTIFICATION = "9+"

    }

    private var iconGoBackToolbar: ImageView? = null
    private var titleToolbar: TextView? = null
    private var searchView: View? = null
    private var iconSearchView: ImageView? = null
    private var iconScanQR: ImageView? = null
    private var iconNotifications: ImageView? = null
    private var iconAmountNotifications: TextView? = null

    init {
        inflate(context, R.layout.view__toolbar_app, this)
        initViews()
    }

    private fun initViews() {
        iconGoBackToolbar = findViewById(R.id.view__toolbar_app__img__go_back)
        titleToolbar = findViewById(R.id.view__toolbar_app__label__title)
        searchView = findViewById(R.id.view__toolbar_app__view__search_bar)
        iconSearchView = findViewById(R.id.view__toolbar_app__img__search)
        iconScanQR = findViewById(R.id.view__toolbar_app__img__scan_qr)
        iconNotifications = findViewById(R.id.view__toolbar_app__img__notification)
        iconAmountNotifications = findViewById(R.id.view__toolbar_app__label__amount_notifications)
    }

    fun setAmountNotifications(amount: Int) {
        iconAmountNotifications?.let {
            with(it) {
                if (amount > MIN_AMOUNT_NOTIFICATION_SHOW) {
                    text = when {
                        amount <= MAX_AMOUNT_NOTIFICATION_SHOW -> {
                            amount.toString()
                        }
                        else -> {
                            STRING_MORE_THAN_MAX_NOTIFICATION
                        }
                    }
                    show()

                } else {
                    invisible()
                }
            }
        }
    }

    private fun getNotifications() =
        iconAmountNotifications?.let {
            with(it) {
                if (
                    !text.isNullOrEmpty() &&
                    text?.isDigitsOnly() == true
                ) {
                    text.toString().toInt()

                } else {
                    ZERO
                }
            }
        } ?: ZERO


    fun setTitleToolbar(title: String) {
        titleToolbar?.text = title
    }

    fun showToolbarDefault() {
        show()
        showDefault()
        hideGoBack()
    }

    fun showToolbarGoBack(title: String = "") {
        show()
        hideDefault()
        showGoBack()
        setTitleToolbar(title)
    }

    fun initToolbarActions(
        actionBack: () -> Unit,
        actionClickScanQR: () -> Unit,
        actionClickIconNotification: () -> Unit,
        actionClickSearchView: () -> Unit
    ) {
        iconGoBackToolbar?.setOnClickListener { actionBack() }
        iconScanQR?.setOnClickListener { actionClickScanQR() }
        iconNotifications?.setOnClickListener { actionClickIconNotification() }
        searchView?.setOnClickListener { actionClickSearchView() }
        iconSearchView?.setOnClickListener { actionClickSearchView() }
    }

    private fun hideDefault() {
        searchView?.hide()
        iconSearchView?.hide()
        iconScanQR?.hide()
        iconNotifications?.hide()
        iconAmountNotifications?.hide()
    }

    private fun hideGoBack() {
        iconGoBackToolbar?.hide()
        titleToolbar?.hide()
    }

    private fun showDefault() {
        searchView?.show()
        iconSearchView?.show()
        iconScanQR?.show()
        iconNotifications?.show()
        iconAmountNotifications?.visible(getNotifications() > MIN_AMOUNT_NOTIFICATION_SHOW)
    }

    private fun showGoBack() {
        iconGoBackToolbar?.show()
        titleToolbar?.show()
    }

}