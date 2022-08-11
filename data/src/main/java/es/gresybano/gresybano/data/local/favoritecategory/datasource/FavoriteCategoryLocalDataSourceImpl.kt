package es.gresybano.gresybano.data.local.favoritecategory.datasource

import es.gresybano.gresybano.data.local.favoritecategory.dao.FavoriteCategoryDao
import es.gresybano.gresybano.data.local.favoritecategory.model.FavoriteCategoryDbo
import es.gresybano.gresybano.data.utils.safeLocalCall
import es.gresybano.gresybano.domain.response.Response

class FavoriteCategoryLocalDataSourceImpl(
    private val favoriteCategoryDao: FavoriteCategoryDao
) : FavoriteCategoryLocalDataSource {

    override suspend fun getCategories(): Response<List<FavoriteCategoryDbo?>?> {
        return safeLocalCall { favoriteCategoryDao.getAll() }
    }

    override suspend fun existCategory(idCategory: Long): Response<Boolean> {
        return safeLocalCall { favoriteCategoryDao.getCategory(idCategory) != null }
    }

    override suspend fun insertListCategories(list: List<FavoriteCategoryDbo>): Response<List<Long?>?> {
        return safeLocalCall { favoriteCategoryDao.insert(*list.toTypedArray()) }
    }

    override suspend fun deleteCategories(list: List<FavoriteCategoryDbo>): Response<Int?> {
        return safeLocalCall { favoriteCategoryDao.delete(*list.toTypedArray()) }
    }

    override suspend fun existCategoryTag(tagCategory: String): Response<Boolean> {
        return safeLocalCall { favoriteCategoryDao.existTag(tagCategory) != null }
    }

}