package es.gresybano.gresybano.data.remote.blockchain.model

import com.google.gson.annotations.SerializedName


data class CryptoTickersDto(
    @SerializedName("last_trade_price")
    val lastTradePrice: Double?,
    @SerializedName("price_24h")
    val price24h: Double?,
    @SerializedName("symbol")
    val symbol: String?,
    @SerializedName("volume_24h")
    val volume24h: Double?,
)