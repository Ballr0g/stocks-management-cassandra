package io.stocks.inc.management.controller

import io.stocks.inc.management.generated.api.StockQuoteApi
import io.stocks.inc.management.generated.model.GetQuoteEntryByIsinResponseDto
import io.stocks.inc.management.generated.model.PostQuoteCreatedResponseDto
import io.stocks.inc.management.generated.model.PostQuoteRequestDto
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RestController

@Validated
@RestController
class StockQuoteController : StockQuoteApi {
    override fun createStockQuoteEntry(postQuoteRequestDto: PostQuoteRequestDto): ResponseEntity<PostQuoteCreatedResponseDto> {
        TODO("Not yet implemented")
    }

    override fun getQuoteEntryByIsin(isin: String): ResponseEntity<GetQuoteEntryByIsinResponseDto> {
        TODO("Not yet implemented")
    }
}
