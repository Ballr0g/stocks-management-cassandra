package io.stocks.inc.management.model

import java.math.BigDecimal
import java.time.LocalDateTime

data class StockQuoteRequest(
    val isin: String,
    val periodId: String,
    val bid: BigDecimal?,
    val ask: BigDecimal?,
    val quoteTime: LocalDateTime,
) {
    init {
        require(bid != null || ask != null) { "Both bid and ask cannot be null" }
        require(isin.length == 12) { "isinStr must be 12 chars, got: $isin (${isin.length} chars)" }
        if (bid != null && ask != null) {
            require(bid < ask) { "bid must be less than ask, got: $bid, $ask" }
        }
    }

    val bidOrAskIfNull: BigDecimal
        get() = bid ?: ask!!
}
