package io.stocks.inc.management.model

import io.stocks.inc.management.exception.IllegalStockQuoteEntryArgumentException
import java.math.BigDecimal

data class StockQuoteEntry(
    val isin: String,
    val bid: BigDecimal?,
    val ask: BigDecimal?,
    val energyLevel: BigDecimal,
) {
    init {
        if (bid == null && ask == null) {
            throw IllegalStockQuoteEntryArgumentException(
                propertyName = "bid, ask",
                actualValue = null.toString(),
                message = "Both bid and ask cannot be null",
            )
        }
        if (isin.length != 12) {
            throw IllegalStockQuoteEntryArgumentException(
                propertyName = "isin",
                actualValue = isin,
                message = "isinStr must be 12 chars, got: $isin (${isin.length} chars)",
            )
        }

        if (bid != null && ask != null && bid >= ask) {
            throw IllegalStockQuoteEntryArgumentException(
                propertyName = "bid, ask",
                actualValue = "$bid >= $ask",
                message = "bid must be < ask, got: $bid, $ask",
            )
        }
    }
}
