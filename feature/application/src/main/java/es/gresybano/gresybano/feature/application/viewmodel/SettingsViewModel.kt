package es.gresybano.gresybano.feature.application.viewmodel

import android.content.Context
import androidx.annotation.StringRes
import es.gresybano.gresybano.common.viewmodel.BaseViewModel
import es.gresybano.gresybano.feature.application.R
import es.gresybano.gresybano.feature.application.view.adapter.ElementSettingsAdapter.ElementSettingVo
import es.gresybano.gresybano.feature.application.view.adapter.ElementSettingsAdapter.IdElementSetting

class SettingsViewModel : BaseViewModel() {

    lateinit var contextToString: (() -> Context)

    private var actionShareApp: (() -> Unit)? = null

    fun saveActionShareApp(actionShareApp: (() -> Unit)) {
        this.actionShareApp = actionShareApp
    }

    fun actionClickInElement(elementClicked: ElementSettingVo) {
        when (elementClicked) {
            is ElementSettingVo.ElementVo -> {
                when (elementClicked.id) {
                    IdElementSetting.NOTIFICATIONS -> {}    //TODO Navegar hacia la pantalla de configuracion de notificaciones
                    IdElementSetting.RATE_US -> {}          //TODO Navegar hacia google play para valorar la app
                    IdElementSetting.SHARE_APP -> {
                        actionShareApp?.invoke()
                    }
                    IdElementSetting.HELP -> {}             //TODO Navegar hacia la pantalla de ayuda
                    IdElementSetting.VERSION -> {}          //TODO Implementar pantalla de versión
                }
            }
            ElementSettingVo.SeparatorVo -> {
                //no-op
            }
        }
    }

    fun getListElementSettings(version: String) =
        listOf(
            ElementSettingVo.ElementVo(
                id = IdElementSetting.NOTIFICATIONS,
                icon = es.gresybano.gresybano.common.R.drawable.icon_notification_off,
                name = getStringVM(R.string.element_notification_settings__title),
                description = getStringVM(R.string.element_notification_settings__description),
            ),
            ElementSettingVo.ElementVo(
                id = IdElementSetting.RATE_US,
                icon = es.gresybano.gresybano.common.R.drawable.icon_star,
                name = getStringVM(R.string.element_rate_us_settings__title),
                description = getStringVM(R.string.element_rate_us_settings__description),
            ),
            ElementSettingVo.ElementVo(
                id = IdElementSetting.SHARE_APP,
                icon = es.gresybano.gresybano.common.R.drawable.icon_share,
                name = getStringVM(R.string.element_share_app_settings__title),
                description = getStringVM(R.string.element_share_app_settings__description),
            ),
            ElementSettingVo.ElementVo(
                id = IdElementSetting.HELP,
                icon = es.gresybano.gresybano.common.R.drawable.icon_help,
                name = getStringVM(R.string.element_help_settings__title),
                description = getStringVM(R.string.element_help_settings__description),
            ),
            ElementSettingVo.SeparatorVo,
            ElementSettingVo.ElementVo(
                id = IdElementSetting.VERSION,
                name = getStringVM(R.string.element_version_settings__name),
                description = version,
            ),
        )

    private fun getStringVM(@StringRes idString: Int) = contextToString().getString(idString)

}