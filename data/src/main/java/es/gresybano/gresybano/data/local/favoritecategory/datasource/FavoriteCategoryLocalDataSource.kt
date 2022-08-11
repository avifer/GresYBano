package es.gresybano.gresybano.data.local.favoritecategory.datasource

import es.gresybano.gresybano.data.local.favoritecategory.model.FavoriteCategoryDbo
import es.gresybano.gresybano.domain.response.Response

interface FavoriteCategoryLocalDataSource {

    suspend fun getCategories(): Response<List<FavoriteCategoryDbo?>?>

    suspend fun existCategory(idCategory: Long): Response<Boolean>

    suspend fun insertListCategories(list: List<FavoriteCategoryDbo>): Response<List<Long?>?>

    suspend fun deleteCategories(list: List<FavoriteCategoryDbo>): Response<Int?>

    suspend fun existCategoryTag(tagCategory: String): Response<Boolean>

}