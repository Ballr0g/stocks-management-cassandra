package io.stocks.inc.management.model

import io.stocks.inc.management.exception.IllegalStockQuoteRequestArgumentException
import io.stocks.inc.management.test.InvalidStockQuoteParamsProvider
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsSource
import java.math.BigDecimal

class StockQuoteRequestTest {
    @Test
    fun `when all properties valid valid StockQuoteRequest is returned`() {
        // given
        // when
        val actualStockQuoteRequest =
            StockQuoteRequest(
                isin = PrimitiveStockQuoteFixtures.TEST_VALID_ISIN,
                periodId = PrimitiveStockQuoteFixtures.TEST_PERIOD_ID,
                quoteTime = PrimitiveStockQuoteFixtures.TEST_ENERGY_LEVEL_UPDATE_TIME,
                bid = PrimitiveStockQuoteFixtures.TEST_VALID_BID,
                ask = PrimitiveStockQuoteFixtures.TEST_VALID_ASK,
            )

        // then
        assertThat(actualStockQuoteRequest).isEqualTo(StockQuoteRequestFixtures.TEST_STOCK_QUOTE_REQUEST)
    }

    @Test
    fun `when StockQuoteRequest contains bid bidOrAskIfNull returns bid`() {
        // given
        val validStockQuoteRequest = StockQuoteRequestFixtures.TEST_STOCK_QUOTE_REQUEST

        // when
        val actualBidOrAskIfNullReturn = validStockQuoteRequest.bidOrAskIfNull

        // then
        assertThat(actualBidOrAskIfNullReturn).isEqualTo(PrimitiveStockQuoteFixtures.TEST_VALID_BID)
    }

    @Test
    fun `when StockQuoteRequest has no bid bidOrAskIfNull returns ask`() {
        // given
        val validStockQuoteRequestNoBid = StockQuoteRequestFixtures.TEST_STOCK_QUOTE_REQUEST.copy(bid = null)

        // when
        val actualBidOrAskIfNullReturn = validStockQuoteRequestNoBid.bidOrAskIfNull

        // then
        assertThat(actualBidOrAskIfNullReturn).isEqualTo(PrimitiveStockQuoteFixtures.TEST_VALID_ASK)
    }

    @ParameterizedTest(name = "[{index}] Incorrect StockQuoteRequest with: isin={0}, bid={1}, ask={2}")
    @ArgumentsSource(InvalidStockQuoteParamsProvider::class)
    fun `when InvalidStockQuoteRequest parameters invalid IllegalStockQuoteRequestArgumentException is thrown`(
        isin: String,
        bid: BigDecimal?,
        ask: BigDecimal?,
    ) {
        // given
        // when
        // then
        assertThatThrownBy {
            StockQuoteRequest(
                isin = isin,
                periodId = PrimitiveStockQuoteFixtures.TEST_PERIOD_ID,
                quoteTime = PrimitiveStockQuoteFixtures.TEST_ENERGY_LEVEL_UPDATE_TIME,
                bid = bid,
                ask = ask,
            )
        }.isInstanceOf(IllegalStockQuoteRequestArgumentException::class.java)
    }
}
