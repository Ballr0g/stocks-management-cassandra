package io.stocks.inc.management.model

import io.stocks.inc.management.exception.IllegalStockQuoteEntryArgumentException
import io.stocks.inc.management.test.InvalidStockQuoteParamsProvider
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsSource
import java.math.BigDecimal

class StockQuoteEntryTest {
    @Test
    fun `when all properties valid valid StockQuoteEntry is returned`() {
        // given
        // when
        val actualStockQuoteEntry =
            StockQuoteEntry(
                isin = PrimitiveStockQuoteFixtures.TEST_VALID_ISIN,
                quoteTime = PrimitiveStockQuoteFixtures.TEST_ENERGY_LEVEL_UPDATE_TIME,
                bid = PrimitiveStockQuoteFixtures.TEST_VALID_BID,
                ask = PrimitiveStockQuoteFixtures.TEST_VALID_ASK,
                energyLevel = PrimitiveStockQuoteFixtures.TEST_VALID_ENERGY_LEVEL,
            )

        // then
        assertThat(actualStockQuoteEntry).isEqualTo(StockQuoteEntryFixtures.TEST_STOCK_QUOTE_ENTRY)
    }

    @ParameterizedTest(name = "[{index}] Incorrect StockQuoteEntry with: isin={0}, bid={1}, ask={2}")
    @ArgumentsSource(InvalidStockQuoteParamsProvider::class)
    fun `when InvalidStockQuoteEntry parameters invalid IllegalStockQuoteEntryArgumentException is thrown`(
        isin: String,
        bid: BigDecimal?,
        ask: BigDecimal?,
    ) {
        assertThatThrownBy {
            StockQuoteEntry(
                isin = isin,
                quoteTime = PrimitiveStockQuoteFixtures.TEST_ENERGY_LEVEL_UPDATE_TIME,
                bid = bid,
                ask = ask,
                energyLevel = PrimitiveStockQuoteFixtures.TEST_VALID_ENERGY_LEVEL,
            )
        }.isInstanceOf(IllegalStockQuoteEntryArgumentException::class.java)
    }
}
