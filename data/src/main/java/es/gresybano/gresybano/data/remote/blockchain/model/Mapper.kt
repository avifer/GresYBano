package es.gresybano.gresybano.data.remote.blockchain.model

import es.gresybano.gresybano.domain.entities.blockchain.CryptoBo

fun CryptoTickersDto.toBo() = CryptoBo(
    symbol ?: "NO_DATA",
    lastTradePrice ?: 0.0,
)