package es.gresybano.gresybano.feature.application.view.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import es.gresybano.gresybano.common.view.BaseFragment
import es.gresybano.gresybano.feature.application.R
import es.gresybano.gresybano.feature.application.databinding.FragmentHelpBinding
import es.gresybano.gresybano.feature.application.view.adapter.ElementHelpAdapter
import es.gresybano.gresybano.feature.application.viewmodel.HelpViewModel

class HelpFragment : BaseFragment() {

    companion object {
        private const val TELEPHONE_TO_CALL = "tel:635849302"
        private const val DIRECTION_SHOP = "google.navigation:q=37.257688,-5.551334"
        private const val PACKAGE_GOOGLE_MAPS = "com.google.android.apps.maps"
    }

    override val viewModel by activityViewModels<HelpViewModel>()

    override fun getBindingCast() = binding as? FragmentHelpBinding

    private val adapterHelpElement = ElementHelpAdapter {
        viewModel.actionClickInElement(it)
    }

    override fun getInflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentHelpBinding.inflate(inflater, container, false)

    override fun onViewReady(savedInstanceState: Bundle?) {
        showToolbarGoBack(getString(R.string.fragment_help__title_toolbar))
        initActionViewModel()
        initList()
        loadDataLists()
    }

    private fun initActionViewModel() {
        with(viewModel) {
            contextToString = { requireContext() }
            saveCallMe {
                Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse(TELEPHONE_TO_CALL)
                    startActivity(this)
                }
            }
            saveFindMe {
                Intent(Intent.ACTION_VIEW, Uri.parse(DIRECTION_SHOP)).apply {
                    setPackage(PACKAGE_GOOGLE_MAPS)
                    startActivity(this)
                }
            }
        }
    }

    private fun initList() {
        getBindingCast()?.let {
            with(it.fragmentHelpListSettings) {
                adapter = adapterHelpElement
                layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }

    private fun loadDataLists() {
        adapterHelpElement.submitList(viewModel.getListElementHelp())
    }

}