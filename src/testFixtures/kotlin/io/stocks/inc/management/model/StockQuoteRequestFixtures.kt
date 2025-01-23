package io.stocks.inc.management.model

object StockQuoteRequestFixtures {
    val TEST_STOCK_QUOTE_REQUEST =
        StockQuoteRequest(
            isin = PrimitiveStockQuoteFixtures.TEST_VALID_ISIN,
            periodId = PrimitiveStockQuoteFixtures.TEST_PERIOD_ID,
            quoteTime = PrimitiveStockQuoteFixtures.TEST_ENERGY_LEVEL_UPDATE_TIME,
            bid = PrimitiveStockQuoteFixtures.TEST_VALID_BID,
            ask = PrimitiveStockQuoteFixtures.TEST_VALID_ASK,
        )
}
