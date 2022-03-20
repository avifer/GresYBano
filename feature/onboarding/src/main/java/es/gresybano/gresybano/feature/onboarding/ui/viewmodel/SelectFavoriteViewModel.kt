package es.gresybano.gresybano.feature.onboarding.ui.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import es.gresybano.gresybano.common.viewmodel.BaseViewModel
import es.gresybano.gresybano.common.viewmodel.executeWithListeners
import es.gresybano.gresybano.domain.category.usecases.GetAllCategoriesLocal
import es.gresybano.gresybano.domain.entities.CategoryBo
import es.gresybano.gresybano.domain.entities.response.ExceptionInfo
import javax.inject.Inject

@HiltViewModel
class SelectFavoriteViewModel @Inject constructor(
    private val getAllCategoriesLocal: GetAllCategoriesLocal
) : BaseViewModel() {

    fun getAllCategories(
        successful: suspend (successful: List<CategoryBo>?) -> Unit,
        error: suspend (error: ExceptionInfo) -> Unit,
        loading: suspend (loading: Boolean) -> Unit,
    ) {
        executeWithListeners(
            successful = successful,
            error = error,
            loading = loading,
            false
        ) { getAllCategoriesLocal() }
    }

}