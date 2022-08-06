package es.gresybano.gresybano.feature.application.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import es.gresybano.gresybano.common.extensions.isUpperToZero
import es.gresybano.gresybano.common.util.DEFAULT_ID_LONG
import es.gresybano.gresybano.common.viewmodel.BaseViewModel
import es.gresybano.gresybano.common.viewmodel.executeWithListeners
import es.gresybano.gresybano.domain.publication.entity.PublicationBo
import es.gresybano.gresybano.domain.response.CodeExceptions
import es.gresybano.gresybano.domain.response.ExceptionInfo
import es.gresybano.gresybano.domain.response.Response
import es.gresybano.gresybano.feature.application.usecases.AddPublicationsToFavoriteUseCase
import es.gresybano.gresybano.feature.application.usecases.IsPublicationFavoriteUseCase
import es.gresybano.gresybano.feature.application.usecases.RemovePublicationsToFavoriteUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class PublicationDetailsViewModel @Inject constructor(
    private val addPublicationsToFavoriteUseCase: AddPublicationsToFavoriteUseCase,
    private val removePublicationsToFavoriteUseCase: RemovePublicationsToFavoriteUseCase,
    private val isPublicationFavoriteUseCase: IsPublicationFavoriteUseCase,
) : BaseViewModel() {

    var idPublication: Long = DEFAULT_ID_LONG
    var listImages: List<String> = listOf()

    private var isFavorite = false

    fun isFavorite() = isFavorite

    fun getIsFavorite(
        successful: suspend (successful: Boolean) -> Unit,
    ) = executeWithListeners(
        successful =
        {
            it?.let {
                isFavorite = it
                successful(it)
            }
        },
        error = {},
        loading = {},
        enableDefaultLoading = false
    ) {
        isPublicationFavoriteUseCase(idPublication)
    }

    fun addOrRemovePublicationToFavorite(
        successful: suspend (successful: Boolean) -> Unit,
    ) {
        executeWithListeners(
            successful =
            {
                it?.let {
                    isFavorite = it
                    successful(it)
                }
            },
            error = {},
            loading = {},
            enableDefaultLoading = false
        ) {
            val publicationToManager = listOf(
                PublicationBo(
                    id = idPublication,
                    category = listOf(),
                    listImages = listOf(),
                )
            )
            flow {
                if (isFavorite) {
                    removePublicationsToFavoriteUseCase(publicationToManager).collect {
                        emit(
                            when (it) {
                                is Response.Error -> Response.Error(it.error)
                                is Response.Loading -> Response.Loading()
                                is Response.Successful -> {
                                    if (it.data.isUpperToZero()) {
                                        Response.Successful(false)

                                    } else {
                                        Response.Error(ExceptionInfo(CodeExceptions.UNKNOWN))
                                    }
                                }
                            }
                        )
                    }

                } else {
                    addPublicationsToFavoriteUseCase(publicationToManager).collect {
                        emit(
                            when (it) {
                                is Response.Error -> Response.Error(it.error)
                                is Response.Loading -> Response.Loading()
                                is Response.Successful -> {
                                    if (!it.data.isNullOrEmpty()) {
                                        Response.Successful(true)

                                    } else {
                                        Response.Error(ExceptionInfo(CodeExceptions.UNKNOWN))
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }

}