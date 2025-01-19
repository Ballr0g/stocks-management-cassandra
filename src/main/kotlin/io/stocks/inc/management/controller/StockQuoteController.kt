package io.stocks.inc.management.controller

import io.stocks.inc.management.generated.api.StockQuoteApi
import io.stocks.inc.management.generated.model.GetQuoteEntryByIsinResponseDto
import io.stocks.inc.management.generated.model.PostQuoteCreatedResponseDto
import io.stocks.inc.management.generated.model.PostQuoteRequestDto
import io.stocks.inc.management.service.StockCreationRequestService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RestController

@Validated
@RestController
class StockQuoteController(
    private val stockCreationRequestService: StockCreationRequestService,
) : StockQuoteApi {
    override fun createStockQuoteEntry(postQuoteRequestDto: PostQuoteRequestDto): ResponseEntity<PostQuoteCreatedResponseDto> =
        ResponseEntity
            .status(HttpStatus.CREATED)
            .body(stockCreationRequestService.handleStockCreation(postQuoteRequestDto))

    override fun getQuoteEntryByIsin(isin: String): ResponseEntity<GetQuoteEntryByIsinResponseDto> {
        TODO("Not yet implemented")
    }
}
