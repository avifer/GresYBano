package es.gresybano.gresybano

import android.os.Bundle
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import dagger.hilt.android.AndroidEntryPoint
import es.gresybano.gresybano.common.view.BaseActivity
import es.gresybano.gresybano.common.view.toast

@AndroidEntryPoint
class HostActivity : BaseActivity() {

    override var viewLoading: ConstraintLayout? = null
    override var progressBarLoading: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)
        viewLoading = findViewById(R.id.activity_host__view__loading)
        progressBarLoading = findViewById(R.id.loading_screen__progress_bar__loading)
        toast(R.string.app_name)
    }

}