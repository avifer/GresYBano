package es.gresybano.gresybano.data.local.blockchain.datasource

import es.gresybano.gresybano.data.local.blockchain.dao.BlockchainDao
import es.gresybano.gresybano.data.local.blockchain.model.toBo
import es.gresybano.gresybano.data.local.blockchain.model.toDbo
import es.gresybano.gresybano.data.utils.BaseRepository
import es.gresybano.gresybano.data.utils.safeLocalCall
import es.gresybano.gresybano.domain.entities.blockchain.CryptoBo
import es.gresybano.gresybano.domain.entities.response.Response

class BlockchainLocalDataSourceImpl(private val blockchainDao: BlockchainDao) :
    BlockchainLocalDataSource, BaseRepository() {
    override suspend fun getLastDataOfCrypto(value: String): Response<CryptoBo?> {
        return safeLocalCall { blockchainDao.getCrypto(value)?.toBo() }
    }

    override suspend fun saveLastDataOfCrypto(value: CryptoBo): Response<List<Long>> {
        return safeLocalCall { blockchainDao.insert(value.toDbo()) }
    }

    override suspend fun updateLastDataOfCrypto(value: CryptoBo): Response<Int> {
        return safeLocalCall { blockchainDao.update(value.toDbo()) }
    }
}