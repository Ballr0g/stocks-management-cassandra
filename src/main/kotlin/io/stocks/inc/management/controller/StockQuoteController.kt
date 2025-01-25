package io.stocks.inc.management.controller

import io.stocks.inc.management.exception.IllegalPropertyArgumentException
import io.stocks.inc.management.extensions.toPostQuoteInvalidFormatResponseDto
import io.stocks.inc.management.generated.api.StockQuoteApi
import io.stocks.inc.management.generated.model.GetQuoteEntryByIsinResponseDto
import io.stocks.inc.management.generated.model.InternalErrorCommonResponseDto
import io.stocks.inc.management.generated.model.InvalidFormatCommonResponseDto
import io.stocks.inc.management.generated.model.PostQuoteCreatedResponseDto
import io.stocks.inc.management.generated.model.PostQuoteRequestDto
import io.stocks.inc.management.service.quote.creation.StockCreationRequestService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.ExceptionHandler
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

    @ExceptionHandler(IllegalPropertyArgumentException::class)
    private fun handleIllegalPropertyArgumentException(
        e: IllegalPropertyArgumentException,
    ): ResponseEntity<InvalidFormatCommonResponseDto> =
        ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(e.toPostQuoteInvalidFormatResponseDto())

    @ExceptionHandler(Throwable::class)
    private fun handleUnexpectedException(
        e: Throwable,
        request: HttpServletRequest,
    ): ResponseEntity<InternalErrorCommonResponseDto> =
        ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(
                InternalErrorCommonResponseDto(
                    e.message ?: "Unidentified error on ${request.method} ${request.requestURL}",
                    "${request.method} ${request.requestURI})",
                ),
            )
}
