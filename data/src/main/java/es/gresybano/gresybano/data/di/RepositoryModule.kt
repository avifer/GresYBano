package es.gresybano.gresybano.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.gresybano.gresybano.data.local.blockchain.dao.BlockchainDao
import es.gresybano.gresybano.data.local.blockchain.datasource.BlockchainLocalDataSource
import es.gresybano.gresybano.data.local.blockchain.datasource.BlockchainLocalDataSourceImpl
import es.gresybano.gresybano.data.remote.blockchain.api.ExchangeApi
import es.gresybano.gresybano.data.remote.blockchain.datasource.BlockchainRemoteDataSource
import es.gresybano.gresybano.data.remote.blockchain.datasource.BlockchainRemoteDataSourceImpl
import es.gresybano.gresybano.data.repository.RepositoryBlockchainImpl
import es.gresybano.gresybano.domain.blockchain.repository.RepositoryBlockchain
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun getBlockchainRemoteDataSource(exchangeApi: ExchangeApi): BlockchainRemoteDataSource {
        return BlockchainRemoteDataSourceImpl(exchangeApi)
    }

    @Provides
    fun getBlockchainLocalDataSource(blockchainDao: BlockchainDao): BlockchainLocalDataSource {
        return BlockchainLocalDataSourceImpl(blockchainDao)
    }

    @Singleton
    @Provides
    fun getRepositoryBlockchainImpl(
        blockchainRemoteDataSource: BlockchainRemoteDataSource,
        blockchainLocalDataSource: BlockchainLocalDataSource
    ): RepositoryBlockchain {
        return RepositoryBlockchainImpl(blockchainRemoteDataSource, blockchainLocalDataSource)
    }

}