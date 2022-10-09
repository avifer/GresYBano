package es.gresybano.gresybano.feature.splash.ui.view.custom

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import es.gresybano.gresybano.domain.splash.entity.VersionControlBo
import es.gresybano.gresybano.feature.splash.R

class DialogVersionControl(
    context: Context,
    private val versionControlBo: VersionControlBo,
    private val actionAccept: () -> Unit,
    private val actionCancel: () -> Unit,
) : MaterialAlertDialogBuilder(context, R.style.AlertDialogTheme) {

    override fun create(): AlertDialog {
        with(versionControlBo) {
            setTitle(title)
            setMessage(description)
            setPositiveButton(textButtonAccept) { _, _ ->
                actionAccept()
            }
            if (textButtonCancel.isNotEmpty()) {
                setNegativeButton(textButtonCancel) { _, _ ->
                    actionCancel()
                }
            }
            setOnCancelListener {
                actionCancel()
            }
            setCancelable(textButtonCancel.isNotEmpty())
        }
        return super.create()
    }

}