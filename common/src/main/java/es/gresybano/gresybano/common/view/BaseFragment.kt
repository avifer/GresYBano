package es.gresybano.gresybano.common.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import es.gresybano.gresybano.common.R
import es.gresybano.gresybano.common.viewmodel.BaseViewModel
import es.gresybano.gresybano.navigation.Navigation
import es.gresybano.gresybano.navigation.Navigation.Back
import es.gresybano.gresybano.navigation.Navigation.ToDirection

abstract class BaseFragment : Fragment() {

    abstract val viewModel: BaseViewModel

    open var binding: ViewBinding? = null

    abstract fun getBindingCast(): ViewBinding?

    protected abstract fun onViewReady(savedInstanceState: Bundle?)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getInflateBinding(inflater, container)
        return binding?.root
    }

    abstract fun getInflateBinding(inflater: LayoutInflater, container: ViewGroup?): ViewBinding?

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDefaultObserverWaiting()
        initDefaultObserverError()
        observeNavigation()
        getHostActivity()?.hideAnimationLoadingError()
        onViewReady(savedInstanceState)
    }

    private fun observeNavigation() {
        viewModel.getNavigation().observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { navigation ->
                handleNavigation(navigation)
            }
        }
    }

    private fun handleNavigation(navCommand: Navigation) {
        when (navCommand) {
            is ToDirection -> findNavController().navigate(navCommand.directions)
            is Back -> findNavController().navigateUp()
        }
    }

    open fun initDefaultObserverWaiting() {
        viewModel.defaultWaitingNotification.observe(viewLifecycleOwner) { responseHandleNull ->
            responseHandleNull?.let { responseHandle ->
                if (responseHandle.getLoadingHandle() != null) {
                    getHostActivity()?.showLoading(responseHandle.loading)
                }
            } ?: kotlin.run { toast(R.string.default_error) }
        }
    }

    open fun initDefaultObserverError() {
        viewModel.defaultErrorNotification.observe(viewLifecycleOwner) { responseHandleNull ->
            responseHandleNull?.let { responseHandle ->
                if (responseHandle.getIdResErrorHandle() != null) {
                    toast(responseHandle.idResError, Toast.LENGTH_LONG)
                    getHostActivity()?.showError(true)
                }
            } ?: kotlin.run { toast(R.string.default_error, Toast.LENGTH_LONG) }
        }
    }

    private fun getHostActivity() = requireActivity() as? BaseActivity

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    fun hideBottomNavigationBar() {
        getHostActivity()?.hideBottomNavigationBar()
    }

    fun showBottomNavigationBar() {
        getHostActivity()?.showBottomNavigationBar()
    }

    fun hideToolbar() {
        getHostActivity()?.hideToolbar()
    }

    fun showToolbarDefault() {
        getHostActivity()?.showToolbarDefault()
    }

    fun showToolbarGoBack(title: String = "", onlyTitle: Boolean = false) {
        getHostActivity()?.showToolbarGoBack(title, onlyTitle)
    }

    fun setToolbarAmountNotifications(amount: Int) {
        getHostActivity()?.setToolbarAmountNotifications(amount)
    }

    fun increaseAmountNotifications() {
        getHostActivity()?.increaseAmountNotifications()
    }

    fun decreaseAmountNotifications() {
        getHostActivity()?.decreaseAmountNotifications()
    }

    fun setTitleToolbar(title: String) {
        getHostActivity()?.setTitleToolbar(title)
    }

    fun newPublicationLiveData() = getHostActivity()?.newPublicationLiveData()

}

fun BaseFragment.toast(
    @StringRes idString: Int,
    duration: Int = Toast.LENGTH_SHORT
) {
    Toast.makeText(
        requireContext(),
        getString(idString),
        duration
    ).show()
}

fun BaseFragment.openAppInGooglePlay() = (requireActivity() as? BaseActivity)?.openAppInGooglePlay()

fun BaseFragment.getVersionName() = (requireActivity() as? BaseActivity)?.versionName ?: ""

fun BaseFragment.getPackageName() = (requireActivity() as? BaseActivity)?.packageName ?: ""

fun BaseFragment.showAlertDialog(
    @StringRes title: Int,
    @StringRes message: Int,
    @StringRes positiveButton: Int,
    @StringRes negativeButton: Int,
    @StringRes actionPositive: () -> Unit,
    @StringRes actionNegative: () -> Unit,
    cancelable: Boolean = false,
) {
    MaterialAlertDialogBuilder(requireContext())
        .setTitle(resources.getString(title))
        .setMessage(resources.getString(message))
        .setCancelable(cancelable)
        .setNegativeButton(resources.getString(negativeButton)) { _, _ ->
            actionNegative()
        }
        .setPositiveButton(resources.getString(positiveButton)) { _, _ ->
            actionPositive()
        }
        .show()
}
