package es.gresybano.gresybano.data.remote.blockchain.datasource

import es.gresybano.gresybano.data.remote.blockchain.api.ExchangeApi
import es.gresybano.gresybano.data.remote.blockchain.model.toBo
import es.gresybano.gresybano.data.utils.BaseRepository
import es.gresybano.gresybano.data.utils.safeRemoteCall
import es.gresybano.gresybano.domain.entities.blockchain.CryptoBo
import es.gresybano.gresybano.domain.entities.response.Response

class BlockchainRemoteDataSourceImpl(private val exchangeApi: ExchangeApi) :
    BlockchainRemoteDataSource, BaseRepository() {

    override suspend fun getDataOfCryptoOnline(value: String): Response<CryptoBo?> {
        return safeRemoteCall { exchangeApi.getData(value).toBo() }
    }

}