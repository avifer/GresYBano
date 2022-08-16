package es.gresybano.gresybano.data.local.favoritecategory.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import es.gresybano.gresybano.data.local.DatabaseApp

@Entity(tableName = DatabaseApp.TABLE_FAVORITES_CATEGORIES_DBO)
data class FavoriteCategoryDbo(
    @PrimaryKey
    @ColumnInfo(name = DatabaseApp.KEY_CATEGORY_DBO)
    val id: String,
    @ColumnInfo(name = DatabaseApp.TAG_CATEGORY_DBO)
    val tag: String?,
)