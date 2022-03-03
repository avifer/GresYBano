package es.gresybano.gresybano.domain.blockchain.usecases

import es.gresybano.gresybano.domain.blockchain.repository.RepositoryBlockchain
import es.gresybano.gresybano.domain.entities.blockchain.CryptoBo
import es.gresybano.gresybano.domain.entities.response.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAndSaveAllDataOfCrypto @Inject constructor(
    private val repositoryBlockchain: RepositoryBlockchain
) {

    operator fun invoke(cryptoPair: String): Flow<Response<CryptoBo>> {
        return flow {
            emit(Response.Loading())
            val priceOnlineCrypto = repositoryBlockchain.getDataOnlineOfCrypto(cryptoPair)
            val priceOfflineCrypto = repositoryBlockchain.getDataOfflineOfCrypto(cryptoPair)

            if (priceOnlineCrypto.isSuccessful()
                && priceOfflineCrypto.isSuccessful()
            ) {
                val priceOnline = priceOnlineCrypto.getData()?.priceOnline ?: 0.0
                val priceOffline = priceOfflineCrypto.getData()?.priceOffline ?: 0.0
                emit(saveDataLocal(CryptoBo(cryptoPair, priceOnline, priceOffline)))

            } else {
                priceOnlineCrypto.getError()?.let {
                    emit(Response.Error<CryptoBo>(it))
                }
                priceOfflineCrypto.getError()?.let {
                    emit(Response.Error<CryptoBo>(it))
                }
            }
        }
    }

    private suspend fun saveDataLocal(crypto: CryptoBo): Response<CryptoBo> {
        return when (val lastDataOfCrypto =
            repositoryBlockchain.getDataOfflineOfCrypto(crypto.name)) {
            is Response.Successful -> {
                val resultSavePrice = if (lastDataOfCrypto.isDataNull()) {
                    repositoryBlockchain.saveDataOfflineOfCrypto(crypto)

                } else {
                    repositoryBlockchain.updateDataOfflineOfCrypto(crypto)
                }
                when (resultSavePrice) {
                    is Response.Successful -> {
                        Response.Successful(crypto)
                    }
                    is Response.Error -> {
                        Response.Error(resultSavePrice.error)
                    }
                    is Response.Loading -> Response.Loading()
                }
            }
            is Response.Error -> {
                Response.Error(lastDataOfCrypto.error)
            }
            is Response.Loading -> Response.Loading()
        }
    }

}