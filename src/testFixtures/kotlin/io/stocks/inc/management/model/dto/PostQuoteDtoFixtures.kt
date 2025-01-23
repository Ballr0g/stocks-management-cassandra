package io.stocks.inc.management.model.dto

import io.stocks.inc.management.exception.IllegalPropertyArgumentException
import io.stocks.inc.management.generated.model.PostQuoteRequestDto
import io.stocks.inc.management.model.PrimitiveStockQuoteFixtures

object PostQuoteDtoFixtures {
    private const val TEST_INVALID_PROPERTY_ISIN = "isin"

    val TEST_POST_QUOTE_REQUEST_DTO =
        PostQuoteRequestDto(
            isin = PrimitiveStockQuoteFixtures.TEST_VALID_ISIN,
            bid = PrimitiveStockQuoteFixtures.TEST_VALID_BID,
            ask = PrimitiveStockQuoteFixtures.TEST_VALID_ASK,
        )

    val TEST_ILLEGAL_PROPERTY_ARGUMENT_EXCEPTION =
        IllegalPropertyArgumentException(
            propertyName = TEST_INVALID_PROPERTY_ISIN,
            actualValue = PrimitiveStockQuoteFixtures.TEST_INVALID_ISIN_TOO_LONG,
            message =
                "isinStr must be 12 chars, got: $PrimitiveStockQuoteFixtures.TEST_INVALID_ISIN_TOO_LONG " +
                    "(${PrimitiveStockQuoteFixtures.TEST_INVALID_ISIN_TOO_LONG.length} chars)",
        )
}
