package io.stocks.inc.management.controller

import io.stocks.inc.management.exception.EnergyLevelNotFoundByIsinException
import io.stocks.inc.management.exception.IllegalStockIsinArgumentException
import io.stocks.inc.management.extensions.toPostQuoteInvalidFormatResponseDto
import io.stocks.inc.management.generated.api.StockQuoteElvlApi
import io.stocks.inc.management.generated.model.GetEnergyLevelByIsinResponseDto
import io.stocks.inc.management.generated.model.InternalErrorCommonResponseDto
import io.stocks.inc.management.generated.model.InvalidFormatCommonResponseDto
import io.stocks.inc.management.generated.model.NotFoundCommonResponseDto
import io.stocks.inc.management.service.elvl.retrieval.EnergyLevelRetrievalService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController

@Validated
@RestController
class StockEnergyLevelController(
    private val energyLevelRetrievalService: EnergyLevelRetrievalService,
) : StockQuoteElvlApi {
    override fun getQuoteEnergyLevelByIsin(isin: String): ResponseEntity<GetEnergyLevelByIsinResponseDto> =
        ResponseEntity.ok(energyLevelRetrievalService.retrieveEnergyLevelByIsin(isin))

    @ExceptionHandler(IllegalStockIsinArgumentException::class)
    private fun handleIllegalPropertyArgumentException(
        e: IllegalStockIsinArgumentException,
    ): ResponseEntity<InvalidFormatCommonResponseDto> =
        ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(e.toPostQuoteInvalidFormatResponseDto())

    @ExceptionHandler(EnergyLevelNotFoundByIsinException::class)
    private fun handleEnergyLevelNotFoundByIsinException(
        e: EnergyLevelNotFoundByIsinException,
        request: HttpServletRequest,
    ): ResponseEntity<NotFoundCommonResponseDto> =
        ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(
                NotFoundCommonResponseDto(
                    missingIdName = e.missingIdName,
                    missingIdValue = e.isinForMissingEnergyLevel,
                    failedEndpoint = "${request.method} ${request.requestURI}",
                ),
            )

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
