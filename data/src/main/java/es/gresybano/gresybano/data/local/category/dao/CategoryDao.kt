package es.gresybano.gresybano.data.local.category.dao

import androidx.room.*
import es.gresybano.gresybano.data.local.DatabaseApp
import es.gresybano.gresybano.data.local.category.model.CategoryDbo

@Dao
interface CategoryDao {

    @Query("SELECT * FROM ${DatabaseApp.TABLE_CATEGORIES_DBO}")
    suspend fun getAll(): List<CategoryDbo?>?

    @Query("SELECT * FROM ${DatabaseApp.TABLE_CATEGORIES_DBO} WHERE ${DatabaseApp.KEY_CATEGORY_DBO} = :id")
    suspend fun getCategory(id: Long): CategoryDbo?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg categories: CategoryDbo): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWithReplace(vararg categories: CategoryDbo): List<Long>

    @Update
    suspend fun update(vararg categories: CategoryDbo): Int

    @Delete
    suspend fun delete(vararg categories: CategoryDbo): Int

}