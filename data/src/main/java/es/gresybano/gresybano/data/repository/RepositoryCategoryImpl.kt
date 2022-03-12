package es.gresybano.gresybano.data.repository

import es.gresybano.gresybano.data.local.category.datasource.CategoryLocalDataSource
import es.gresybano.gresybano.data.local.category.model.toBo
import es.gresybano.gresybano.data.local.category.model.toDbo
import es.gresybano.gresybano.data.remote.category.datasource.CategoryRemoteDataSource
import es.gresybano.gresybano.data.utils.BaseRepository
import es.gresybano.gresybano.domain.category.repository.RepositoryCategory
import es.gresybano.gresybano.domain.entities.category.CategoryBo
import es.gresybano.gresybano.domain.entities.response.Response
import es.gresybano.gresybano.domain.entities.response.defaultResponse

class RepositoryCategoryImpl(
    private val categoryRemoteDataSource: CategoryRemoteDataSource,
    private val categoryLocalDataSource: CategoryLocalDataSource,
) : RepositoryCategory, BaseRepository() {

    //TODO MOCK
    private val listCategoriesMock = listOf(
        CategoryBo(
            1,
            "Cuartos de baño",
            "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
        ),
        CategoryBo(
            2,
            "Cuartos de baño",
            "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
        ),
        CategoryBo(
            3,
            "Cuartos de baño",
            "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
        ),
        CategoryBo(
            4,
            "Cuartos de baño",
            "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
        ),
        CategoryBo(
            5,
            "Cuartos de baño",
            "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
        ),
        CategoryBo(
            6,
            "Cuartos de baño",
            "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
        )
    )

    override suspend fun getAllCategoriesRemote(): Response<List<CategoryBo>> {
        //TODO MOCK
        return Response.Successful(listCategoriesMock)
        /*
        return categoryRemoteDataSource.getAllCategories().defaultResponse { listCategories ->
            listCategories?.filterNotNull()?.map { category -> category.toBo() } ?: listOf()
        }
        */
    }

    override suspend fun getAllCategoriesLocal(): Response<List<CategoryBo>> {
        return categoryLocalDataSource.getAllCategories().defaultResponse { listCategories ->
            listCategories?.filterNotNull()?.map { category -> category.toBo() } ?: listOf()
        }
    }

    override suspend fun getCategoryRemote(id: Long): Response<CategoryBo?> {
        //TODO MOCK
        return Response.Successful(
            CategoryBo(
                id,
                "Cuartos de baño",
                "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
            )
        )
    }

    override suspend fun getCategoryLocal(id: Long): Response<CategoryBo?> {
        return categoryLocalDataSource.getCategory(id)
            .defaultResponse { category -> category?.toBo() }
    }

    override suspend fun insertCategoriesLocal(list: List<CategoryBo>): Response<List<Long>> {
        return categoryLocalDataSource.insertCategories((list.map { it.toDbo() }))
            .defaultResponse { idsCategories -> idsCategories?.filterNotNull() ?: listOf() }
    }

    override suspend fun insertWithReplaceCategoriesLocal(list: List<CategoryBo>): Response<List<Long>> {
        return categoryLocalDataSource.insertWithReplaceCategories((list.map { it.toDbo() }))
            .defaultResponse { idsCategories -> idsCategories?.filterNotNull() ?: listOf() }
    }

    override suspend fun updateCategoriesLocal(list: List<CategoryBo>): Response<Int> {
        return categoryLocalDataSource.updateCategories((list.map { it.toDbo() }))
            .defaultResponse { numbersRowAffected -> numbersRowAffected ?: 0 }
    }

    override suspend fun deleteCategoriesLocal(list: List<CategoryBo>): Response<Int> {
        return categoryLocalDataSource.deleteCategories((list.map { it.toDbo() }))
            .defaultResponse { numbersRowAffected -> numbersRowAffected ?: 0 }
    }

}