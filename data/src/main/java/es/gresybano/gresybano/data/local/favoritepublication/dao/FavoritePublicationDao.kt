package es.gresybano.gresybano.data.local.favoritepublication.dao

import androidx.room.*
import es.gresybano.gresybano.data.local.DatabaseApp
import es.gresybano.gresybano.data.local.favoritepublication.model.FavoritePublicationDbo

@Dao
interface FavoritePublicationDao {

    @Query("SELECT * FROM ${DatabaseApp.TABLE_FAVORITES_PUBLICATIONS_DBO} WHERE ${DatabaseApp.KEY_PUBLICATION_DBO} = :id")
    suspend fun getPublication(id: Long): FavoritePublicationDbo?

    @Query("SELECT * FROM ${DatabaseApp.TABLE_FAVORITES_PUBLICATIONS_DBO}")
    suspend fun getAll(): List<FavoritePublicationDbo?>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg favoriteCategories: FavoritePublicationDbo): List<Long?>?

    @Delete
    suspend fun delete(vararg favoriteCategories: FavoritePublicationDbo): Int?

}