package es.gresybano.gresybano.domain.blockchain.repository

import es.gresybano.gresybano.domain.entities.blockchain.CryptoBo
import es.gresybano.gresybano.domain.entities.response.Response

interface RepositoryBlockchain {

    suspend fun getDataOnlineOfCrypto(value: String): Response<CryptoBo?>

    suspend fun getDataOfflineOfCrypto(value: String): Response<CryptoBo?>

    suspend fun saveDataOfflineOfCrypto(value: CryptoBo): Response<List<Long>>

    suspend fun updateDataOfflineOfCrypto(value: CryptoBo): Response<Int>

}