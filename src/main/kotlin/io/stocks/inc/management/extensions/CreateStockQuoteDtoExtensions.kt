package io.stocks.inc.management.extensions

import io.stocks.inc.management.generated.model.PostQuoteCreatedResponseDto
import io.stocks.inc.management.generated.model.PostQuoteRequestDto
import io.stocks.inc.management.model.PeriodIdProvider
import io.stocks.inc.management.model.StockQuoteEntry
import io.stocks.inc.management.model.StockQuoteRequest
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId

fun PostQuoteRequestDto.toModelWithPeriodIdProvider(
    periodIdProvider: PeriodIdProvider =
        PeriodIdProvider {
            val currentTimeUtc = LocalDate.now(ZoneId.of("UTC"))
            "${currentTimeUtc.dayOfYear}-${currentTimeUtc.monthValue}-${currentTimeUtc.year}"
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
