package io.stocks.inc.management.extensions

import io.stocks.inc.management.extensions.DefaultFormatters.DEFAULT_PERIOD_ID_FORMATTER
import io.stocks.inc.management.generated.model.PostQuoteCreatedResponseDto
import io.stocks.inc.management.generated.model.PropertyValidationError
import io.stocks.inc.management.model.PrimitiveStockQuoteFixtures
import io.stocks.inc.management.model.StockQuoteEntryFixtures
import io.stocks.inc.management.model.StockQuoteRequest
import io.stocks.inc.management.model.dto.PostQuoteDtoFixtures
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.from
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.Month

class CreateStockQuoteDtoExtensionsTest {
    @Test
    fun `when PostQuoteRequestDto mapped to StockQuoteRequest properties transferred`() {
        // given
        val testLocalDate = LocalDate.of(2025, Month.JANUARY, 20)
        val expectedPeriodId: String = testLocalDate.format(DEFAULT_PERIOD_ID_FORMATTER)

        // when
        val actualMappedStockQuoteRequest =
            PostQuoteDtoFixtures.TEST_POST_QUOTE_REQUEST_DTO.toModelWithPeriodIdProvider {
                testLocalDate.format(DEFAULT_PERIOD_ID_FORMATTER)
            }

        // then
        assertThat(actualMappedStockQuoteRequest)
            .returns(PrimitiveStockQuoteFixtures.TEST_VALID_ISIN, from(StockQuoteRequest::isin))
            .returns(expectedPeriodId, from(StockQuoteRequest::periodId))
            .returns(PrimitiveStockQuoteFixtures.TEST_VALID_BID, from(StockQuoteRequest::bid))
            .returns(PrimitiveStockQuoteFixtures.TEST_VALID_ASK, from(StockQuoteRequest::ask))
    }

    @Test
    fun `when StockQuoteEntry mapped to PostQuoteCreatedResponseDto properties transferred`() {
        // given
        // when
        val actualMappedPostQuoteCreatedResponseDto = StockQuoteEntryFixtures.TEST_STOCK_QUOTE_ENTRY.toDto()

        assertThat(actualMappedPostQuoteCreatedResponseDto)
            .returns(PrimitiveStockQuoteFixtures.TEST_VALID_ISIN, from(PostQuoteCreatedResponseDto::isin))
            .returns(PrimitiveStockQuoteFixtures.TEST_VALID_ENERGY_LEVEL, from(PostQuoteCreatedResponseDto::elvl))
    }

    @Test
    fun `when IllegalPropertyArgumentException mapped to PostQuoteInvalidFormatResponseDto properties wrapped into a list`() {
        // given
        val mappedIllegalPropertyArgumentException = PostQuoteDtoFixtures.TEST_ILLEGAL_PROPERTY_ARGUMENT_EXCEPTION
        val expectedPropertyValidationError =
            PropertyValidationError(
                propertyName = mappedIllegalPropertyArgumentException.propertyName,
                actualValue = mappedIllegalPropertyArgumentException.actualValue,
                expectedFormat = mappedIllegalPropertyArgumentException.message,
            )

        // when
        val actualMappedPostQuoteInvalidFormatResponseDto = mappedIllegalPropertyArgumentException.toPostQuoteInvalidFormatResponseDto()

        // then
        assertThat(actualMappedPostQuoteInvalidFormatResponseDto.validationErrors)
            .hasSize(1)
            .containsExactly(expectedPropertyValidationError)
    }
}
