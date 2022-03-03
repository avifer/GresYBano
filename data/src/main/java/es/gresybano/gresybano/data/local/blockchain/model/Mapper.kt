package es.gresybano.gresybano.data.local.blockchain.model

import es.gresybano.gresybano.domain.entities.blockchain.CryptoBo

fun CryptoDbo.toBo() = CryptoBo(
    keyCrypto,
    0.0,
    lastPrice
)

fun CryptoBo.toDbo() = CryptoDbo(
    name,
    priceOnline ?: 0.0
)