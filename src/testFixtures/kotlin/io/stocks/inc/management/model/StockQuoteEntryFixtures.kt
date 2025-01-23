package io.stocks.inc.management.model

object StockQuoteEntryFixtures {
    val TEST_STOCK_QUOTE_ENTRY =
        StockQuoteEntry(
            isin = PrimitiveStockQuoteFixtures.TEST_VALID_ISIN,
            quoteTime = PrimitiveStockQuoteFixtures.TEST_ENERGY_LEVEL_UPDATE_TIME,
            bid = PrimitiveStockQuoteFixtures.TEST_VALID_BID,
            ask = PrimitiveStockQuoteFixtures.TEST_VALID_ASK,
            energyLevel = PrimitiveStockQuoteFixtures.TEST_VALID_ENERGY_LEVEL,
        )
}
