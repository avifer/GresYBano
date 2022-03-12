package es.gresybano.gresybano.data.local.category.datasource

import es.gresybano.gresybano.data.local.category.dao.CategoryDao
import es.gresybano.gresybano.data.local.category.model.CategoryDbo
import es.gresybano.gresybano.data.utils.safeLocalCall
import es.gresybano.gresybano.domain.entities.response.Response

class CategoryLocalDataSourceImpl(private val categoryDao: CategoryDao) : CategoryLocalDataSource {

    override suspend fun getAllCategories(): Response<List<CategoryDbo?>?> {
        return safeLocalCall { categoryDao.getAll() }
    }

    override suspend fun getCategory(id: Long): Response<CategoryDbo?> {
        return safeLocalCall { categoryDao.getCategory(id) }
    }

    override suspend fun insertCategories(list: List<CategoryDbo>): Response<List<Long?>?> {
        return safeLocalCall { categoryDao.insert(*list.toTypedArray()) }
    }

    override suspend fun insertWithReplaceCategories(list: List<CategoryDbo>): Response<List<Long?>?> {
        return safeLocalCall { categoryDao.insertWithReplace(*list.toTypedArray()) }
    }

    override suspend fun updateCategories(list: List<CategoryDbo>): Response<Int> {
        return safeLocalCall { categoryDao.update(*list.toTypedArray()) }
    }

    override suspend fun deleteCategories(list: List<CategoryDbo>): Response<Int> {
        return safeLocalCall { categoryDao.delete(*list.toTypedArray()) }
    }

}