package es.gresybano.gresybano.data.local.favoritecategory.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import es.gresybano.gresybano.data.local.DatabaseApp

@Entity(tableName = DatabaseApp.TABLE_FAVORITES_CATEGORIES_DBO)
data class FavoriteCategoryDbo(
    @PrimaryKey
    @ColumnInfo(name = DatabaseApp.KEY_CATEGORY_DBO)
    val id: Long,
    @ColumnInfo(name = DatabaseApp.TAG_CATEGORY_DBO)
    val tag: String?,
    @ColumnInfo(name = DatabaseApp.NAME_CATEGORY_DBO)
    val name: String,
    @ColumnInfo(name = DatabaseApp.URL_CATEGORY_DBO)
    val mainImage: String,
)