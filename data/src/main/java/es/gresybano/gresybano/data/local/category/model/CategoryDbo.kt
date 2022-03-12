package es.gresybano.gresybano.data.local.category.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import es.gresybano.gresybano.data.local.DatabaseApp

@Entity(tableName = DatabaseApp.TABLE_CATEGORIES_DBO)
data class CategoryDbo(
    @PrimaryKey
    @ColumnInfo(name = DatabaseApp.KEY_CATEGORY_DBO)
    val id: Long,
    @ColumnInfo(name = DatabaseApp.NAME_CATEGORY_DBO)
    val name: String,
    @ColumnInfo(name = DatabaseApp.URL_CATEGORY_DBO)
    val url: String,
    @ColumnInfo(name = DatabaseApp.FAVORITE_CATEGORY_DBO)
    val isFavorite: Boolean,
)