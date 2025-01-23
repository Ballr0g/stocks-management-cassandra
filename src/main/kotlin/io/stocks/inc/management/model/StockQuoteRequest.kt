package io.stocks.inc.management.model

import io.stocks.inc.management.exception.IllegalStockQuoteRequestArgumentException
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
        if (bid == null && ask == null) {
            throw IllegalStockQuoteRequestArgumentException(
                propertyName = "bid, ask",
                actualValue = null.toString(),
                message = "Both bid and ask cannot be null",
            )
        }
        if (isin.length != 12) {
            throw IllegalStockQuoteRequestArgumentException(
                propertyName = "isin",
                actualValue = isin,
                message = "isinStr must be 12 chars, got: $isin (${isin.length} chars)",
            )
        }
        if (bid != null && ask != null && bid >= ask) {
            throw IllegalStockQuoteRequestArgumentException(
                propertyName = "bid, ask",
                actualValue = "$bid >= $ask",
                message = "bid must be < ask, got: $bid, $ask",
            )
        }
    }

    val bidOrAskIfNull: BigDecimal
        get() = bid ?: ask!!
}
