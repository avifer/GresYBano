package es.gresybano.gresybano.feature.application.usecases

import es.gresybano.gresybano.domain.category.repository.RepositoryCategory
import es.gresybano.gresybano.domain.entities.HomeListElementsVo
import es.gresybano.gresybano.domain.entities.response.Response
import es.gresybano.gresybano.domain.entities.response.getData
import es.gresybano.gresybano.domain.entities.response.getError
import es.gresybano.gresybano.domain.entities.response.isSuccessful
import es.gresybano.gresybano.domain.publication.repository.RepositoryPublication
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetDataHomeFragmentUseCase @Inject constructor(
    private val repositoryCategory: RepositoryCategory,
    private val repositoryPublication: RepositoryPublication,
) {

    operator fun invoke(): Flow<Response<HomeListElementsVo>> {
        return flow {
            emit(Response.Loading())
            //TODO MOCK
            Thread.sleep(3000)

            val listAllCategories = repositoryCategory.getAllCategoriesRemote()
            val listMorePopular = repositoryPublication.getMorePopularPublicationsRemote()
            val listLastPublished = repositoryPublication.getLastPublishedPublicationsRemote()

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