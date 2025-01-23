package io.stocks.inc.management.model

import io.stocks.inc.management.exception.IllegalStockQuoteEnergyLevelArgumentException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class StockQuoteEnergyLevelTest {
    @Test
    fun `when all properties valid valid StockQuoteEnergyLevel is returned`() {
        // given
        // when
        val actualStockQuoteEnergyLevel =
            StockQuoteEnergyLevel(
                isin = PrimitiveStockQuoteFixtures.TEST_VALID_ISIN,
                energyLevel = PrimitiveStockQuoteFixtures.TEST_VALID_ENERGY_LEVEL,
                quoteTime = PrimitiveStockQuoteFixtures.TEST_ENERGY_LEVEL_UPDATE_TIME,
            )

        // then
        assertThat(actualStockQuoteEnergyLevel).isEqualTo(StockQuoteEnergyLevelFixtures.TEST_STOCK_QUOTE_ENERGY_LEVEL)
    }

    @Test
    fun `when isin format not 12 symbols IllegalStockQuoteEnergyLevelArgumentException thrown`() {
        // given
        // when
        // then
        assertThrows<IllegalStockQuoteEnergyLevelArgumentException> {
            StockQuoteEnergyLevel(
                isin = PrimitiveStockQuoteFixtures.TEST_INVALID_ISIN_TOO_LONG,
                energyLevel = PrimitiveStockQuoteFixtures.TEST_VALID_ENERGY_LEVEL,
                quoteTime = PrimitiveStockQuoteFixtures.TEST_ENERGY_LEVEL_UPDATE_TIME,
            )
        }
    }
}
