package es.gresybano.gresybano.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import es.gresybano.gresybano.data.local.favoritecategory.dao.FavoriteCategoryDao
import es.gresybano.gresybano.data.local.favoritecategory.model.FavoriteCategoryDbo
import es.gresybano.gresybano.data.local.favoritepublication.dao.FavoritePublicationDao
import es.gresybano.gresybano.data.local.favoritepublication.model.FavoritePublicationDbo

@Database(entities = [FavoriteCategoryDbo::class, FavoritePublicationDbo::class], version = 1)
abstract class DatabaseApp : RoomDatabase() {

    abstract fun favoriteCategoryDao(): FavoriteCategoryDao
    abstract fun favoritePublicationDao(): FavoritePublicationDao

    companion object {
        const val DATABASE_NAME = "GresYBanoArahalDataBase"

        //Categories
        const val TABLE_FAVORITES_CATEGORIES_DBO = "favoriteCategories"
        const val KEY_CATEGORY_DBO = "id"
        const val TAG_CATEGORY_DBO = "tag"

        //Publications
        const val TABLE_FAVORITES_PUBLICATIONS_DBO = "favoritePublications"
        const val KEY_PUBLICATION_DBO = "id"

    }
}
