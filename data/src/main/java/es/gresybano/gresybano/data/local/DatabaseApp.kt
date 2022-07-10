package es.gresybano.gresybano.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import es.gresybano.gresybano.data.local.favoritecategory.dao.FavoriteCategoryDao
import es.gresybano.gresybano.data.local.favoritecategory.model.FavoriteCategoryDbo

@Database(entities = [FavoriteCategoryDbo::class], version = 1)
abstract class DatabaseApp : RoomDatabase() {

    abstract fun categoryDao(): FavoriteCategoryDao

    companion object {
        const val DATABASE_NAME = "GresYBanoArahalDataBase"

        //Categories
        const val TABLE_FAVORITES_CATEGORIES_DBO = "favoriteCategories"
        const val KEY_CATEGORY_DBO = "id"
        const val NAME_CATEGORY_DBO = "name"
        const val URL_CATEGORY_DBO = "mainImage"
        const val FAVORITE_CATEGORY_DBO = "isFavorite"

    }
}
