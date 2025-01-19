package io.stocks.inc.management.model

import java.math.BigDecimal
import java.time.LocalDateTime

data class StockQuoteRequest(
    val isin: String,
    val periodId: String,
    val bid: BigDecimal,
    val ask: BigDecimal,
    val quoteTime: LocalDateTime,
) {
    init {
        require(isin.length == 12) { "isinStr must be 12 chars, got: $isin (${isin.length} chars)" }
        require(bid < ask) { "bid must be less than ask, got: $bid, $ask" }
    }
}
