package es.gresybano.gresybano.feature.application.viewmodel

import android.content.Context
import androidx.annotation.StringRes
import es.gresybano.gresybano.common.viewmodel.BaseViewModel
import es.gresybano.gresybano.feature.application.R
import es.gresybano.gresybano.feature.application.view.adapter.ElementSettingsAdapter.ElementSettingVo
import es.gresybano.gresybano.feature.application.view.adapter.ElementSettingsAdapter.IdElementSetting

class SettingsViewModel : BaseViewModel() {

    fun actionClickInElement(elementClicked: ElementSettingVo) {
        when (elementClicked) {
            is ElementSettingVo.ElementVo -> {
                when (elementClicked.id) {
                    IdElementSetting.NOTIFICATIONS -> {}    //TODO Navegar hacia la pantalla de configuracion de notificaciones
                    IdElementSetting.RANK_US -> {}          //TODO Navegar hacia google play para valorar la app
                    IdElementSetting.SHARE_APP -> {}        //TODO Lanzar intent para compartir la app
                    IdElementSetting.HELP -> {}             //TODO Navegar hacia la pantalla de ayuda
                    IdElementSetting.VERSION -> {}          //TODO Implementar pantalla de versión
                }
            }
            ElementSettingVo.SeparatorVo -> {
                //no-op
            }
        }
    }

    fun getListElementSettings(context: Context, version: String) =
        listOf(
            ElementSettingVo.ElementVo(
                id = IdElementSetting.NOTIFICATIONS,
                icon = es.gresybano.gresybano.common.R.drawable.icon_notification_off,
                name = getStringVM(context, R.string.element_notification_settings__title),
                description = getStringVM(
                    context,
                    R.string.element_notification_settings__description
                ),
            ),
            ElementSettingVo.ElementVo(
                id = IdElementSetting.RANK_US,
                icon = es.gresybano.gresybano.common.R.drawable.icon_star,
                name = getStringVM(context, R.string.element_rank_us_settings__title),
                description = getStringVM(context, R.string.element_rank_us_settings__description),
            ),
            ElementSettingVo.ElementVo(
                id = IdElementSetting.SHARE_APP,
                icon = es.gresybano.gresybano.common.R.drawable.icon_share,
                name = getStringVM(context, R.string.element_share_app_settings__title),
                description = getStringVM(
                    context,
                    R.string.element_share_app_settings__description
                ),
            ),
            ElementSettingVo.ElementVo(
                id = IdElementSetting.HELP,
                icon = es.gresybano.gresybano.common.R.drawable.icon_help,
                name = getStringVM(context, R.string.element_help_settings__title),
                description = getStringVM(context, R.string.element_help_settings__description),
            ),
            ElementSettingVo.SeparatorVo,
            ElementSettingVo.ElementVo(
                id = IdElementSetting.VERSION,
                name = getStringVM(context, R.string.element_version_settings__name),
                description = version,
            ),
        )

    private fun getStringVM(context: Context, @StringRes idString: Int) =
        context.getString(idString)

}