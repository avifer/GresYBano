package es.gresybano.gresybano.feature.application.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import es.gresybano.gresybano.common.view.BaseFragment
import es.gresybano.gresybano.common.view.toast
import es.gresybano.gresybano.domain.notification.entity.MessageNotificationBo
import es.gresybano.gresybano.feature.application.R
import es.gresybano.gresybano.feature.application.databinding.FragmentNotificationsBinding
import es.gresybano.gresybano.feature.application.view.adapter.NotificationsListAdapter
import es.gresybano.gresybano.feature.application.viewmodel.NotificationsViewModel

@AndroidEntryPoint
class NotificationsFragment : BaseFragment() {

    override val viewModel by activityViewModels<NotificationsViewModel>()

    override fun getBindingCast() = binding as? FragmentNotificationsBinding

    private val adapterListNotifications = NotificationsListAdapter {
        if (!it.isOpened) {
            decreaseAmountNotifications()
        }
        it.isOpened = true
        when (it) {
            is MessageNotificationBo.NewPublicationBo -> viewModel.goToDetailPublication(it)
            MessageNotificationBo.Error -> toast(es.gresybano.gresybano.domain.R.string.unknown_error)
        }
    }

    override fun onViewReady(savedInstanceState: Bundle?) {
        showToolbarGoBack(getString(R.string.fragment_notifications__title_toolbar))
        initList()
        loadDataLists()
        observeNewNotifications()
    }

    private fun observeNewNotifications() {
        newPublicationLiveData()?.observe(viewLifecycleOwner) {
            loadDataLists()
        }
    }

    private fun initList() {
        getBindingCast()?.let {
            with(it.fragmentNotificationsListNotifications) {
                adapter = adapterListNotifications
                layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }

    private fun loadDataLists() {
        viewModel.getAllNotifications().observe(viewLifecycleOwner) {
            it?.let { adapterListNotifications.addElements(it) }
        }
    }

    override fun getInflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentNotificationsBinding.inflate(inflater, container, false)

    override fun onDestroyView() {
        super.onDestroyView()
        getBindingCast()?.fragmentNotificationsListNotifications?.adapter = null
    }

}