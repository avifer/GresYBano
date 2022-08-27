package es.gresybano.gresybano.feature.application.viewmodel

import android.content.Context
import androidx.annotation.StringRes
import es.gresybano.gresybano.common.viewmodel.BaseViewModel
import es.gresybano.gresybano.feature.application.R
import es.gresybano.gresybano.feature.application.view.adapter.ElementHelpAdapter
import es.gresybano.gresybano.feature.application.view.adapter.ElementHelpAdapter.ElementHelpVo


class HelpViewModel : BaseViewModel() {

    lateinit var contextToString: (() -> Context)

    private var callMe: (() -> Unit)? = null

    private var findMe: (() -> Unit)? = null

    fun saveCallMe(callMe: (() -> Unit)) {
        this.callMe = callMe
    }

    fun saveFindMe(findMe: (() -> Unit)) {
        this.findMe = findMe
    }

    fun actionClickInElement(elementHelpVo: ElementHelpVo) {
        when (elementHelpVo) {
            is ElementHelpVo.ElementVo -> {
                when (elementHelpVo.id) {
                    ElementHelpAdapter.IdElementHelp.CALL_ME -> {
                        callMe?.invoke()
                    }
                    ElementHelpAdapter.IdElementHelp.FIND_ME -> {
                        findMe?.invoke()
                    }
                }
            }
            ElementHelpVo.SeparatorVo -> {
                //no-op
            }
        }
    }

    fun getListElementHelp() =
        listOf(
            ElementHelpVo.ElementVo(
                id = ElementHelpAdapter.IdElementHelp.CALL_ME,
                icon = es.gresybano.gresybano.common.R.drawable.icon_call,
                name = getStringVM(R.string.element_help_call_me__title),
                description = getStringVM(R.string.element_help_call_me__subtitle),
            ),
            ElementHelpVo.ElementVo(
                id = ElementHelpAdapter.IdElementHelp.FIND_ME,
                icon = es.gresybano.gresybano.common.R.drawable.icon_maps,
                name = getStringVM(R.string.element_help_find_me__title),
                description = getStringVM(R.string.element_help_find_me__subtitle),
            )
        )

    private fun getStringVM(@StringRes idString: Int) = contextToString().getString(idString)

}