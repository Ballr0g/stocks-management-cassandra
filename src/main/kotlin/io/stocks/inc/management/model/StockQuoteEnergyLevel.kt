package io.stocks.inc.management.model

import io.stocks.inc.management.exception.IllegalStockQuoteEnergyLevelArgumentException
import java.math.BigDecimal
import java.time.LocalDateTime

data class StockQuoteEnergyLevel(
    val isin: String,
    val energyLevel: BigDecimal,
    val quoteTime: LocalDateTime,
) {
    init {
        if (isin.length != 12) {
            throw IllegalStockQuoteEnergyLevelArgumentException(
                actualValue = isin,
                message = "isin must be 12 chars, got: $isin (${isin.length} chars)",
            )
        }
    }
}
