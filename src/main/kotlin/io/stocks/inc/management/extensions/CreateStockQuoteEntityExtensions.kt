package io.stocks.inc.management.extensions

import io.stocks.inc.management.model.StockQuoteEnergyLevel
import io.stocks.inc.management.model.StockQuoteRequest
import io.stocks.inc.management.persistence.entity.StockQuoteByIsinAndDateEntity
import io.stocks.inc.management.persistence.entity.StockQuoteEnergyLevelEntity

fun StockQuoteRequest.toEntityWithEnergyLevel(stockQuoteEnergyLevel: StockQuoteEnergyLevel) =
    StockQuoteByIsinAndDateEntity(
        isin = this.isin,
        periodId = this.periodId,
        quoteTime = this.quoteTime,
        bid = this.bid,
        ask = this.ask,
        energyLevel = stockQuoteEnergyLevel.energyLevel,
    )

fun StockQuoteEnergyLevel.toEntity() =
    StockQuoteEnergyLevelEntity(
        isin = this.isin,
        energyLevel = this.energyLevel,
        quoteTime = this.quoteTime,
    )

fun StockQuoteEnergyLevelEntity?.toModel() =
    if (this == null) {
        null
    } else {
        StockQuoteEnergyLevel(
            isin = this.isin,
            energyLevel = this.energyLevel,
            quoteTime = this.quoteTime,
        )
    }
