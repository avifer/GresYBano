package es.gresybano.gresybano.data.local.blockchain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import es.gresybano.gresybano.data.local.DatabaseApp

@Entity(tableName = DatabaseApp.TABLE_CRYPTO_DBO)
data class CryptoDbo(
    @PrimaryKey
    @ColumnInfo(name = DatabaseApp.KEY_CRYPTO_CRYPTO_DBO)
    val keyCrypto: String,
    @ColumnInfo(name = DatabaseApp.LAST_PRICE_CRYPTO_DBO)
    val lastPrice: Double,
)
