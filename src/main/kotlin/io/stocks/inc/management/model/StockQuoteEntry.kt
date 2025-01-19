package io.stocks.inc.management.model

import java.math.BigDecimal

data class StockQuoteEntry(
    val isin: String,
    val bid: BigDecimal,
    val ask: BigDecimal,
    val energyLevel: BigDecimal,
) {
    init {
        require(isin.length == 12) { "isinStr must be 12 chars, got: $isin (${isin.length} chars)" }
        require(bid < ask) { "bid must be less than ask, got: $bid, $ask" }
    }
}
