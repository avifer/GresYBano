package es.gresybano.gresybano.data.repository

import es.gresybano.gresybano.data.local.blockchain.datasource.BlockchainLocalDataSource
import es.gresybano.gresybano.data.remote.blockchain.datasource.BlockchainRemoteDataSource
import es.gresybano.gresybano.data.utils.BaseRepository
import es.gresybano.gresybano.domain.blockchain.repository.RepositoryBlockchain
import es.gresybano.gresybano.domain.entities.blockchain.CryptoBo
import es.gresybano.gresybano.domain.entities.response.Response


class RepositoryBlockchainImpl(
    private val blockchainRemoteDataSource: BlockchainRemoteDataSource,
    private val blockchainLocalDataSource: BlockchainLocalDataSource
) : RepositoryBlockchain, BaseRepository() {
    override suspend fun getDataOnlineOfCrypto(value: String): Response<CryptoBo?> {
        return blockchainRemoteDataSource.getDataOfCryptoOnline(value)
    }

    override suspend fun getDataOfflineOfCrypto(value: String): Response<CryptoBo?> {
        return blockchainLocalDataSource.getLastDataOfCrypto(value)
    }

    override suspend fun saveDataOfflineOfCrypto(value: CryptoBo): Response<List<Long>> {
        return blockchainLocalDataSource.saveLastDataOfCrypto(value)
    }

    override suspend fun updateDataOfflineOfCrypto(value: CryptoBo): Response<Int> {
        return blockchainLocalDataSource.updateLastDataOfCrypto(value)
    }
}