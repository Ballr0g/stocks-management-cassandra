package io.stocks.inc.management.model.entity

import io.stocks.inc.management.model.PrimitiveStockQuoteFixtures
import io.stocks.inc.management.persistence.entity.StockQuoteByIsinAndDateEntity
import io.stocks.inc.management.persistence.entity.StockQuoteEnergyLevelEntity

object PostQuoteEntityFixtures {
    val TEST_VALID_STOCK_QUOTE_BY_ISIN_AND_DATE_ENTITY =
        StockQuoteByIsinAndDateEntity(
            isin = PrimitiveStockQuoteFixtures.TEST_VALID_ISIN,
            periodId = PrimitiveStockQuoteFixtures.TEST_PERIOD_ID,
            quoteTime = PrimitiveStockQuoteFixtures.TEST_ENERGY_LEVEL_UPDATE_TIME,
            bid = PrimitiveStockQuoteFixtures.TEST_VALID_BID,
            ask = PrimitiveStockQuoteFixtures.TEST_VALID_ASK,
            energyLevel = PrimitiveStockQuoteFixtures.TEST_VALID_ENERGY_LEVEL,
        )

    val TEST_STOCK_QUOTE_ENERGY_LEVEL_ENTITY =
        StockQuoteEnergyLevelEntity(
            isin = PrimitiveStockQuoteFixtures.TEST_VALID_ISIN,
            energyLevel = PrimitiveStockQuoteFixtures.TEST_VALID_ENERGY_LEVEL,
            quoteTime = PrimitiveStockQuoteFixtures.TEST_ENERGY_LEVEL_UPDATE_TIME,
        )
}
