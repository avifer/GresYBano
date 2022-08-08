package es.gresybano.gresybano.common.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import es.gresybano.gresybano.common.R
import es.gresybano.gresybano.common.extensions.hide
import es.gresybano.gresybano.common.extensions.invisible
import es.gresybano.gresybano.common.extensions.show
import es.gresybano.gresybano.common.extensions.visible
import es.gresybano.gresybano.common.util.EMPTY_STRING
import es.gresybano.gresybano.common.util.ZERO

class ToolbarGresYBano(context: Context, attributeSet: AttributeSet) :
    ConstraintLayout(context, attributeSet) {

    companion object {
        private const val MIN_AMOUNT_NOTIFICATION_SHOW = 1
        private const val MAX_AMOUNT_NOTIFICATION_SHOW = 9
        private const val STRING_MORE_THAN_MAX_NOTIFICATION = "9+"
    }

    private enum class TypeToolbarShow {
        TOOLBAR_DEFAULT, TOOLBAR_GO_BACK, NONE
    }

    private var iconGoBackToolbar: ImageView? = null
    private var titleToolbar: TextView? = null
    private var searchView: View? = null
    private var iconSearchView: ImageView? = null
    private var iconScanQR: ImageView? = null
    private var iconNotifications: ImageView? = null
    private var iconAmountNotifications: TextView? = null

    private var amountNotifications: Int = ZERO

    private var typeToolbarShow: TypeToolbarShow = TypeToolbarShow.TOOLBAR_DEFAULT

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

    fun increaseAmountNotifications() {
        setAmountNotifications(++amountNotifications)
    }

    fun decreaseAmountNotifications() {
        if (amountNotifications > ZERO) {
            amountNotifications--
        }
        setAmountNotifications(amountNotifications)
    }

    fun setAmountNotifications(amount: Int) {
        amountNotifications = amount
        iconAmountNotifications?.let {
            with(it) {
                val result = calculateAmountNotificationToShow()
                text = result
                if (typeToolbarShow == TypeToolbarShow.TOOLBAR_DEFAULT) {
                    when (result) {
                        EMPTY_STRING -> invisible()
                        else -> show()
                    }
                }
            }
        }
    }

    private fun calculateAmountNotificationToShow() =
        when (amountNotifications) {
            in Int.MIN_VALUE..0 -> EMPTY_STRING
            in MIN_AMOUNT_NOTIFICATION_SHOW..MAX_AMOUNT_NOTIFICATION_SHOW -> amountNotifications.toString()
            else -> STRING_MORE_THAN_MAX_NOTIFICATION
        }

    fun setTitleToolbar(title: String) {
        titleToolbar?.text = title
    }

    fun hideToolbar() {
        typeToolbarShow = TypeToolbarShow.NONE
        this.invisible()
    }

    fun showToolbarDefault() {
        show()
        hideGoBack()
        showDefault()
    }

    fun showToolbarGoBack(title: String = "", onlyTitle: Boolean = false) {
        show()
        hideDefault()
        showGoBack(onlyTitle)
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
        typeToolbarShow = TypeToolbarShow.NONE
        //searchView?.hide()    //TODO Descomentar cuando este desarrollado
        //iconSearchView?.hide()    //TODO Descomentar cuando este desarrollado
        //iconScanQR?.hide()    //TODO Descomentar cuando este desarrollado
        iconNotifications?.hide()
        iconAmountNotifications?.hide()
    }

    private fun hideGoBack() {
        typeToolbarShow = TypeToolbarShow.NONE
        iconGoBackToolbar?.hide()
        titleToolbar?.hide()
    }

    private fun showDefault() {
        typeToolbarShow = TypeToolbarShow.TOOLBAR_DEFAULT
        //searchView?.show()  //TODO Descomentar cuando este desarrollado
        //iconSearchView?.show()  //TODO Descomentar cuando este desarrollado
        //iconScanQR?.show()   //TODO Descomentar cuando este desarrollado
        iconNotifications?.show()
        iconAmountNotifications?.visible(amountNotifications != ZERO)
    }

    private fun showGoBack(onlyTitle: Boolean = false) {
        typeToolbarShow = TypeToolbarShow.TOOLBAR_GO_BACK
        if (!onlyTitle) {
            iconGoBackToolbar?.show()
        }
        titleToolbar?.show()
    }

}