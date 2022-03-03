package es.gresybano.gresybano.data.local.blockchain.datasource

import es.gresybano.gresybano.domain.entities.blockchain.CryptoBo
import es.gresybano.gresybano.domain.entities.response.Response

interface BlockchainLocalDataSource {

    suspend fun getLastDataOfCrypto(value: String): Response<CryptoBo?>

    suspend fun saveLastDataOfCrypto(value: CryptoBo): Response<List<Long>>

    suspend fun updateLastDataOfCrypto(value: CryptoBo): Response<Int>

}