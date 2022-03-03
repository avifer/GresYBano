package es.gresybano.gresybano.data.remote.blockchain.datasource

import es.gresybano.gresybano.domain.entities.blockchain.CryptoBo
import es.gresybano.gresybano.domain.entities.response.Response

interface BlockchainRemoteDataSource {

    suspend fun getDataOfCryptoOnline(value: String): Response<CryptoBo?>

}