package es.gresybano.gresybano.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import es.gresybano.gresybano.data.local.category.dao.CategoryDao
import es.gresybano.gresybano.data.local.category.model.CategoryDbo

@Database(entities = [CategoryDbo::class], version = 1)
abstract class DatabaseApp : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao

    companion object {
        const val DATABASE_NAME = "GresYBanoArahalDataBase"

        //Categories
        const val TABLE_CATEGORIES_DBO = "categories"
        const val KEY_CATEGORY_DBO = "id"
        const val NAME_CATEGORY_DBO = "name"
        const val URL_CATEGORY_DBO = "url"
        const val FAVORITE_CATEGORY_DBO = "favorite"

    }
}
