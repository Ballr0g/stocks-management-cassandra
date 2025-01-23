package io.stocks.inc.management.model

object StockQuoteEnergyLevelFixtures {
    val TEST_STOCK_QUOTE_ENERGY_LEVEL =
        StockQuoteEnergyLevel(
            isin = PrimitiveStockQuoteFixtures.TEST_VALID_ISIN,
            energyLevel = PrimitiveStockQuoteFixtures.TEST_VALID_ENERGY_LEVEL,
            quoteTime = PrimitiveStockQuoteFixtures.TEST_ENERGY_LEVEL_UPDATE_TIME,
        )
}
