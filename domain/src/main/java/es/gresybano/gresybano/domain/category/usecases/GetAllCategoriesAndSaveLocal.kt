package es.gresybano.gresybano.domain.category.usecases

import es.gresybano.gresybano.domain.category.repository.RepositoryCategory
import es.gresybano.gresybano.domain.entities.CategoryBo
import es.gresybano.gresybano.domain.entities.response.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllCategoriesAndSaveLocal @Inject constructor(
    private val repositoryCategory: RepositoryCategory
) {

    operator fun invoke(): Flow<Response<Boolean>> {
        return flow {
            emit(Response.Loading())
            //TODO MOCK
            Thread.sleep(3000)
            val listCategories = repositoryCategory.getAllCategoriesRemote()
            if (listCategories.isSuccessful() && listCategories.getData() != null) {
                emit(insertCategoriesLocal(listCategories))

            } else {
                emit(
                    Response.Error(
                        listCategories.getError() ?: ExceptionInfo(CodeExceptions.UNKNOWN)
                    )
                )
            }
        }
    }

    private suspend fun insertCategoriesLocal(listCategories: Response<List<CategoryBo>>): Response<Boolean> {
        return listCategories.getData()?.let {
            val rowsAffected = repositoryCategory.insertCategoriesLocal(it)

            if (rowsAffected.isSuccessful() && rowsAffected.getData() != null) {
                rowsAffected.getData()?.let {
                    Response.Successful(rowsAffected.isSuccessful())

                } ?: run {
                    Response.Error(ExceptionInfo(CodeExceptions.UNKNOWN))
                }

            } else {
                Response.Error(listCategories.getError() ?: ExceptionInfo(CodeExceptions.UNKNOWN))
            }

        } ?: Response.Error(ExceptionInfo(CodeExceptions.UNKNOWN))
    }

}