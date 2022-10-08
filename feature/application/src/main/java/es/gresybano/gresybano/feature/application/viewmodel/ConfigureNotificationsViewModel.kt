package es.gresybano.gresybano.feature.application.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.gresybano.gresybano.common.usecase.*
import es.gresybano.gresybano.common.util.TAG_ALL_FIREBASE
import es.gresybano.gresybano.common.viewmodel.BaseViewModel
import es.gresybano.gresybano.common.viewmodel.defaultResponse
import es.gresybano.gresybano.domain.category.entity.CategoryBo
import es.gresybano.gresybano.feature.application.usecases.GetAllCategoriesRemoteUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConfigureNotificationsViewModel @Inject constructor(
    private val getAllCategoriesRemoteUseCase: GetAllCategoriesRemoteUseCase,
    private val addCategoriesToFavoriteUseCase: AddCategoriesToFavoriteUseCase,
    private val removeCategoriesToFavoriteUseCase: RemoveCategoriesToFavoriteUseCase,
    private val isNotificationsEnableUseCase: IsNotificationsEnableUseCase,
    private val saveTagToFavoriteUseCase: SaveTagToFavoriteUseCase,
    private val removeTagToFavoriteUseCase: RemoveTagToFavoriteUseCase,
) : BaseViewModel() {

    fun isNotificationsEnable(): LiveData<Boolean?> {
        return defaultResponse { isNotificationsEnableUseCase() }
    }

    fun getCategories(): LiveData<List<CategoryBo>?> {
        return defaultResponse(getAllCategoriesRemoteUseCase()) { listCategories ->
            listCategories?.filter { !it.tag.isNullOrEmpty() }
        }
    }

    fun addOrRemoveCategoryFavorite(categoryBo: CategoryBo) {
        viewModelScope.launch {
            when (categoryBo.isFavorite) {
                true -> addCategoriesToFavoriteUseCase(listOf(categoryBo)).collect { /*nop*/ }
                false -> removeCategoriesToFavoriteUseCase(listOf(categoryBo)).collect { /*nop*/ }
                null -> {}
            }
        }
    }

    fun enableNotifications() {
        viewModelScope.launch {
            saveTagToFavoriteUseCase(TAG_ALL_FIREBASE).collect { /*noop*/ }
        }
    }

    fun disableNotifications(listCategories: List<CategoryBo>) {
        viewModelScope.launch {
            removeTagToFavoriteUseCase(TAG_ALL_FIREBASE).collect { /*noop*/ }
            removeCategoriesToFavoriteUseCase(listCategories).collect { /*noop*/ }
        }
    }

}