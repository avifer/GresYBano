package es.gresybano.gresybano.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import es.gresybano.gresybano.data.local.blockchain.model.CryptoDbo
import es.gresybano.gresybano.data.local.blockchain.dao.BlockchainDao

@Database(entities = [CryptoDbo::class], version = 1)
abstract class DatabaseApp : RoomDatabase() {

    abstract fun blockchainDao(): BlockchainDao

    companion object {
        const val DATABASE_NAME = "blockchainDB"

        //CryptoDbo
        const val TABLE_CRYPTO_DBO = "cryptos"
        const val KEY_CRYPTO_CRYPTO_DBO = "key_crypto"
        const val LAST_PRICE_CRYPTO_DBO = "last_price"

    }
}
