package es.gresybano.gresybano.data.local.favoritepublication.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import es.gresybano.gresybano.data.local.DatabaseApp

@Entity(tableName = DatabaseApp.TABLE_FAVORITES_PUBLICATIONS_DBO)
data class FavoritePublicationDbo(
    @PrimaryKey
    @ColumnInfo(name = DatabaseApp.KEY_PUBLICATION_DBO)
    val id: Long,
)