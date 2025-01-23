package io.stocks.inc.management.test

import io.stocks.inc.management.model.PrimitiveStockQuoteFixtures
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import java.util.stream.Stream

class InvalidStockQuoteParamsProvider : ArgumentsProvider {
    override fun provideArguments(context: ExtensionContext): Stream<Arguments> =
        Stream.of(
            Arguments.of(
                PrimitiveStockQuoteFixtures.TEST_INVALID_ISIN_TOO_SHORT,
                PrimitiveStockQuoteFixtures.TEST_VALID_BID,
                PrimitiveStockQuoteFixtures.TEST_VALID_ASK,
            ),
            Arguments.of(
                PrimitiveStockQuoteFixtures.TEST_INVALID_ISIN_TOO_LONG,
                PrimitiveStockQuoteFixtures.TEST_VALID_BID,
                PrimitiveStockQuoteFixtures.TEST_VALID_ASK,
            ),
            Arguments.of(
                PrimitiveStockQuoteFixtures.TEST_VALID_ISIN,
                PrimitiveStockQuoteFixtures.TEST_VALID_BID,
                PrimitiveStockQuoteFixtures.TEST_ASK_EQUAL_TO_BID,
            ),
            Arguments.of(
                PrimitiveStockQuoteFixtures.TEST_VALID_ISIN,
                PrimitiveStockQuoteFixtures.TEST_VALID_BID,
                PrimitiveStockQuoteFixtures.TEST_ASK_LESS_THAN_BID,
            ),
            Arguments.of(
                PrimitiveStockQuoteFixtures.TEST_VALID_ISIN,
                null,
                null,
            ),
        )
}
