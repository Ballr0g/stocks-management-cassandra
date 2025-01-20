package io.stocks.inc.management.service

import io.stocks.inc.management.extensions.toDto
import io.stocks.inc.management.extensions.toModelWithPeriodIdProvider
import io.stocks.inc.management.generated.model.PostQuoteCreatedResponseDto
import io.stocks.inc.management.generated.model.PostQuoteRequestDto
import org.springframework.stereotype.Service

@Service
class StockCreationRequestService(
    private val stockQuoteSavingHandler: StockQuoteSavingHandler,
) {
    fun handleStockCreation(postQuoteRequestDto: PostQuoteRequestDto): PostQuoteCreatedResponseDto {
        val stockQuoteRequest = postQuoteRequestDto.toModelWithPeriodIdProvider()
        val savedStockQuoteEntry = stockQuoteSavingHandler.addStockQuoteEntry(stockQuoteRequest)
        return savedStockQuoteEntry.toDto()
    }
}
