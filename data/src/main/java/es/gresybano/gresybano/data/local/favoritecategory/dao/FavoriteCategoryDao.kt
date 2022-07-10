package es.gresybano.gresybano.data.local.favoritecategory.dao

import androidx.room.*
import es.gresybano.gresybano.data.local.DatabaseApp
import es.gresybano.gresybano.data.local.favoritecategory.model.FavoriteCategoryDbo

@Dao
interface FavoriteCategoryDao {

    @Query("SELECT * FROM ${DatabaseApp.TABLE_FAVORITES_CATEGORIES_DBO} WHERE ${DatabaseApp.KEY_CATEGORY_DBO} = :id")
    suspend fun getCategory(id: Long): FavoriteCategoryDbo?

    @Query("SELECT * FROM ${DatabaseApp.TABLE_FAVORITES_CATEGORIES_DBO}")
    suspend fun getAll(): List<FavoriteCategoryDbo?>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg favoriteCategories: FavoriteCategoryDbo): List<Long?>?

    @Delete
    suspend fun delete(vararg favoriteCategories: FavoriteCategoryDbo): Int?

}