package es.gresybano.gresybano.feature.application.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import es.gresybano.gresybano.common.view.BaseFragment
import es.gresybano.gresybano.common.view.getPackageName
import es.gresybano.gresybano.common.view.getVersionName
import es.gresybano.gresybano.feature.application.R
import es.gresybano.gresybano.feature.application.databinding.FragmentSettingsBinding
import es.gresybano.gresybano.feature.application.view.adapter.ElementSettingsAdapter
import es.gresybano.gresybano.feature.application.viewmodel.SettingsViewModel


class SettingsFragment : BaseFragment() {

    override val viewModel by viewModels<SettingsViewModel>()

    override fun getBindingCast() = binding as? FragmentSettingsBinding

    private val adapterSettingsElement = ElementSettingsAdapter {
        viewModel.actionClickInElement(it)
    }

    override fun onViewReady(savedInstanceState: Bundle?) {
        showToolbarGoBack(getString(R.string.fragment_settings__title_toolbar), true)
        showBottomNavigationBar()
        initActionViewModel()
        initList()
        loadDataLists()
    }

    private fun initActionViewModel() {
        with(viewModel) {
            contextToString = { requireContext() }
            saveActionShareApp {
                Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(
                        Intent.EXTRA_TEXT,
                        getString(R.string.text_share_app, getPackageName())
                    )
                    type = "text/plain"
                    Intent.createChooser(this, getString(R.string.share_by))
                    startActivity(this)
                }
            }
        }
    }

    private fun initList() {
        getBindingCast()?.let {
            with(it.fragmentSettingsListSettings) {
                adapter = adapterSettingsElement
                layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }

    private fun loadDataLists() {
        adapterSettingsElement.submitList(
            viewModel.getListElementSettings(getVersionName())
        )
    }

    override fun getInflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentSettingsBinding.inflate(inflater, container, false)

}