package io.stocks.inc.management.extensions

import io.stocks.inc.management.exception.IllegalPropertyArgumentException
import io.stocks.inc.management.extensions.DefaultFormatters.DEFAULT_PERIOD_ID_FORMATTER
import io.stocks.inc.management.generated.model.InvalidFormatCommonResponseDto
import io.stocks.inc.management.generated.model.PostQuoteCreatedResponseDto
import io.stocks.inc.management.generated.model.PostQuoteRequestDto
import io.stocks.inc.management.generated.model.PropertyValidationError
import io.stocks.inc.management.model.PeriodIdProvider
import io.stocks.inc.management.model.StockQuoteEntry
import io.stocks.inc.management.model.StockQuoteRequest
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object DefaultFormatters {
    val DEFAULT_PERIOD_ID_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
}

fun PostQuoteRequestDto.toModelWithPeriodIdProvider(
    periodIdProvider: PeriodIdProvider =
        PeriodIdProvider {
            val currentTimeUtc = LocalDate.now(ZoneId.of("UTC"))
            currentTimeUtc.format(DEFAULT_PERIOD_ID_FORMATTER)
        },
) = StockQuoteRequest(
    isin = this.isin,
    periodId = periodIdProvider.currentPeriodId(),
    bid = bid,
    ask = ask,
    quoteTime = LocalDateTime.now(ZoneId.of("UTC")),
)

fun StockQuoteEntry.toDto() =
    PostQuoteCreatedResponseDto(
        isin = this.isin,
        elvl = energyLevel,
    )

fun IllegalPropertyArgumentException.toPostQuoteInvalidFormatResponseDto() =
    InvalidFormatCommonResponseDto(
        listOf(
            PropertyValidationError(
                propertyName = propertyName,
                actualValue = actualValue,
                expectedFormat = message,
            ),
        ),
    )
