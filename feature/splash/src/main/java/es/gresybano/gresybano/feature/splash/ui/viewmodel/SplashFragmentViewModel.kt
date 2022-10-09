package es.gresybano.gresybano.feature.splash.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.gresybano.gresybano.common.util.ZERO_LONG
import es.gresybano.gresybano.common.util.parseToJSON
import es.gresybano.gresybano.common.util.runDelayMain
import es.gresybano.gresybano.common.viewmodel.BaseViewModel
import es.gresybano.gresybano.common.viewmodel.defaultResponse
import es.gresybano.gresybano.domain.category.entity.CategoryBo
import es.gresybano.gresybano.domain.response.Response
import es.gresybano.gresybano.domain.splash.entity.VersionControlBo
import es.gresybano.gresybano.feature.splash.ui.view.fragment.SplashFragmentDirections
import es.gresybano.gresybano.feature.splash.usecases.GetAllCategoriesUseCase
import es.gresybano.gresybano.feature.splash.usecases.GetIfOnBoardingConfigUseCase
import es.gresybano.gresybano.feature.splash.usecases.GetMinVersionUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashFragmentViewModel @Inject constructor(
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase,
    private val getIfOnBoardingConfigUseCase: GetIfOnBoardingConfigUseCase,
    private val getMinVersionUseCase: GetMinVersionUseCase,
) : BaseViewModel() {

    companion object {
        private const val DELAY_SPLASH = 1500L
    }

    private val showPopUp = MutableLiveData<VersionControlBo>()
    private var versionControlGetter: VersionControlBo? = null

    private fun goToOnBoarding(
        listCategories: List<CategoryBo>?,
        delay: Long = ZERO_LONG,
    ) {
        runDelayMain(delay) {
            navigate(
                SplashFragmentDirections.navigateFromSplashFeatureToOnboardingFeature(
                    parseToJSON(listCategories?.toTypedArray())
                )
            )
        }
    }

    private fun goToHome() {
        runDelayMain(DELAY_SPLASH) {
            navigate(SplashFragmentDirections.navigateFromSplashFeatureToApplicationFeature())
        }
    }

    private fun getAllCategories() {
        defaultResponse(getAllCategoriesUseCase(), false) {
            goToOnBoarding(it)
            it
        }
    }

    fun checkVersionControl(versionName: String) {
        viewModelScope.launch {
            getMinVersionUseCase().collect {
                when (it) {
                    is Response.Successful -> {
                        if (it.data != null &&
                            versionName < it.data!!.minVersion
                        ) {
                            versionControlGetter = it.data
                            showPopUp.postValue(it.data)

                        } else {
                            navigateToFirstScreen()
                        }

                    }
                    is Response.Error -> {
                        navigateToFirstScreen()
                    }
                    is Response.Loading -> {
                        //no-op
                    }
                }
            }
        }
    }

    fun navigateToFirstScreen() {
        defaultResponse(getIfOnBoardingConfigUseCase(), false) {
            if (it == false) {
                getAllCategories()

            } else {
                goToHome()
            }
            it
        }
    }

    fun getShowPopUpLiveData() = showPopUp as LiveData<VersionControlBo>
    fun getVersionControlGetter() = versionControlGetter

}