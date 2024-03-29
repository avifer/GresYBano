package es.gresybano.gresybano.feature.application.usecases

import es.gresybano.gresybano.domain.category.repository.CategoryRepository
import es.gresybano.gresybano.feature.application.entity.HomeListElementsVo
import es.gresybano.gresybano.domain.response.*
import es.gresybano.gresybano.domain.publication.repository.PublicationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetDataHomeFragmentUseCase @Inject constructor(
    private val repositoryCategory: CategoryRepository,
    private val repositoryPublication: PublicationRepository,
) {

    operator fun invoke(): Flow<Response<HomeListElementsVo>> {
        return flow {
            emit(Response.Loading())
            val listAllCategories = repositoryCategory.getAllCategoriesFull()
            val listMorePopular = repositoryPublication.getMorePopularPublications()
            val listLastPublished = repositoryPublication.getLastPublishedPublications()

            if (listAllCategories.isSuccessful()
                || listMorePopular.isSuccessful()
                || listLastPublished.isSuccessful()
            ) {
                emit(
                    Response.Successful(
                        HomeListElementsVo(
                            (listAllCategories.getData() ?: emptyList()),
                            (listMorePopular.getData() ?: emptyList()),
                            (listLastPublished.getData() ?: emptyList()),
                        )
                    )
                )

            } else {
                listAllCategories.getError()?.let {
                    emit(Response.Error(it))
                }
                listMorePopular.getError()?.let {
                    emit(Response.Error(it))
                }
                listLastPublished.getError()?.let {
                    emit(Response.Error(it))
                }
            }
        }
    }
}