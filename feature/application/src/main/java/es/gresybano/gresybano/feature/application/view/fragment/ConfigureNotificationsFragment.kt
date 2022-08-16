package es.gresybano.gresybano.feature.application.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import es.gresybano.gresybano.common.view.BaseFragment
import es.gresybano.gresybano.common.view.showAlertDialog
import es.gresybano.gresybano.domain.category.entity.CategoryBo
import es.gresybano.gresybano.feature.application.R
import es.gresybano.gresybano.feature.application.databinding.FragmentConfigureNotificationsBinding
import es.gresybano.gresybano.feature.application.view.adapter.AvailableCategoriesNotificationsAdapter
import es.gresybano.gresybano.feature.application.viewmodel.ConfigureNotificationsViewModel

@AndroidEntryPoint
class ConfigureNotificationsFragment : BaseFragment() {

    override val viewModel by viewModels<ConfigureNotificationsViewModel>()

    private val actionPositive: (id: CategoryBo) -> Unit = {
        viewModel.enableNotifications()
        getBindingCast()?.fragmentConfigureNotificationsSwitchGeneral?.isChecked = true
        viewModel.addOrRemoveCategoryFavorite(it)
    }

    private val actionNegative: (id: String) -> Unit = {
        adapterCategories.disableCategory(it)
    }

    private val adapterCategories = AvailableCategoriesNotificationsAdapter {
        if (getBindingCast()?.fragmentConfigureNotificationsSwitchGeneral?.isChecked == false) {
            showAlertDialog(
                title = R.string.notifications_is_disable,
                message = R.string.want_enable_notifications,
                negativeButton = R.string.cancel,
                positiveButton = R.string.activate,
                actionNegative = { actionNegative(it.id) },
                actionPositive = { actionPositive(it) },
            )

        } else {
            viewModel.addOrRemoveCategoryFavorite(it)
        }
    }

    override fun getBindingCast() = binding as? FragmentConfigureNotificationsBinding

    override fun getInflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentConfigureNotificationsBinding.inflate(inflater, container, false)

    override fun onViewReady(savedInstanceState: Bundle?) {
        showToolbarGoBack(getString(R.string.fragment_configure_notifications__title_toolbar))
        initList()
        initDataList()
        initClickSwitchNotifications()
    }

    private fun initList() {
        getBindingCast()?.let {
            with(it.fragmentConfigureNotificationsListCategories) {
                adapter = adapterCategories
                layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }

    private fun initDataList() {
        viewModel.isNotificationsEnable().observe(viewLifecycleOwner) { enable ->
            enable?.let { notificationsEnable ->
                getBindingCast()
                    ?.fragmentConfigureNotificationsSwitchGeneral
                    ?.isChecked = notificationsEnable

                viewModel.getCategories().observe(viewLifecycleOwner) { listCategories ->
                    adapterCategories.submitList(
                        if (notificationsEnable) {
                            listCategories

                        } else {
                            listCategories?.map { it.apply { it.isFavorite = false } }
                        }
                    )

                }
            }
        }
    }

    private fun initClickSwitchNotifications() {
        getBindingCast()?.fragmentConfigureNotificationsSwitchGeneral?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                viewModel.enableNotifications()

            } else {
                viewModel.disableNotifications(adapterCategories.currentList)
                adapterCategories.removeAllFavorites()
            }
        }
    }

}