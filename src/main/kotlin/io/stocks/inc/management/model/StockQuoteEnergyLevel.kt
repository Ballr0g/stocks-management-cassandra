package io.stocks.inc.management.model

import java.math.BigDecimal
import java.time.LocalDateTime

data class StockQuoteEnergyLevel(
    val isin: String,
    val energyLevel: BigDecimal,
    val quoteTime: LocalDateTime,
) {
    init {
        require(isin.length == 12) { "isin must be 12 chars, got: $isin (${isin.length} chars)" }
    }
}
