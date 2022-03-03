package es.gresybano.gresybano.data.local.blockchain.dao

import androidx.room.*
import es.gresybano.gresybano.data.local.DatabaseApp
import es.gresybano.gresybano.data.local.blockchain.model.CryptoDbo

@Dao
interface BlockchainDao {

    @Query("SELECT * FROM ${DatabaseApp.TABLE_CRYPTO_DBO}")
    suspend fun getAll(): List<CryptoDbo?>?

    @Query("SELECT * FROM ${DatabaseApp.TABLE_CRYPTO_DBO} WHERE ${DatabaseApp.KEY_CRYPTO_CRYPTO_DBO} = :keyValue")
    suspend fun getCrypto(keyValue: String): CryptoDbo?

    @Insert
    suspend fun insert(vararg users: CryptoDbo): List<Long>

    @Update
    suspend fun update(vararg users: CryptoDbo): Int

    @Delete
    suspend fun delete(user: CryptoDbo): Int

}
