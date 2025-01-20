package io.stocks.inc.management.service

import io.stocks.inc.management.model.StockQuoteEnergyLevel
import io.stocks.inc.management.model.StockQuoteRequest
import io.stocks.inc.management.persistence.repository.StockQuoteCreationScenarioRepository
import org.springframework.stereotype.Component

@Component
class StockEnergyLevelRetriever(
    private val stockQuoteCreationScenarioRepository: StockQuoteCreationScenarioRepository,
) {
    fun calculateNewEnergyLevelForStockQuoteRequest(stockQuoteRequest: StockQuoteRequest): StockQuoteEnergyLevel {
        val previousEnergyLevelQuote =
            stockQuoteCreationScenarioRepository.selectEnergyLevelByIsin(stockQuoteRequest.isin)
                ?: return inferEnergyLevelForNewStockByRequest(stockQuoteRequest)

        val previousEnergyLevel = previousEnergyLevelQuote.energyLevel
        return previousEnergyLevelQuote.copy(
            energyLevel =
                when {
                    stockQuoteRequest.bid > previousEnergyLevel -> stockQuoteRequest.bid
                    stockQuoteRequest.ask < previousEnergyLevel -> stockQuoteRequest.ask
                    else -> previousEnergyLevel
                },
        )
    }

    private fun inferEnergyLevelForNewStockByRequest(stockQuoteRequest: StockQuoteRequest) =
        StockQuoteEnergyLevel(
            isin = stockQuoteRequest.isin,
            energyLevel = stockQuoteRequest.bid,
            quoteTime = stockQuoteRequest.quoteTime,
        )
}
