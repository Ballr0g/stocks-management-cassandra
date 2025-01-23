package io.stocks.inc.management.service

import io.stocks.inc.management.extensions.toModel
import io.stocks.inc.management.model.StockQuoteEnergyLevel
import io.stocks.inc.management.model.StockQuoteRequest
import io.stocks.inc.management.persistence.repository.StockQuoteCreationScenarioRepository
import org.springframework.stereotype.Component

@Component
class StockEnergyLevelRetriever(
    private val stockQuoteCreationScenarioRepository: StockQuoteCreationScenarioRepository,
) {
    fun calculateNewEnergyLevelForStockQuoteRequest(stockQuoteRequest: StockQuoteRequest): StockQuoteEnergyLevel {
        val previousEnergyLevelQuoteEntity =
            stockQuoteCreationScenarioRepository.selectEnergyLevelByIsin(stockQuoteRequest.isin)

        val previousEnergyLevelModel =
            previousEnergyLevelQuoteEntity.toModel()
                ?: return inferEnergyLevelForNewStockByRequest(stockQuoteRequest)

        val previousEnergyLevel = previousEnergyLevelModel.energyLevel
        return previousEnergyLevelModel.copy(
            energyLevel =
                when {
                    stockQuoteRequest.bid == null || stockQuoteRequest.ask == null -> stockQuoteRequest.bidOrAskIfNull
                    stockQuoteRequest.bid > previousEnergyLevel -> stockQuoteRequest.bid
                    stockQuoteRequest.ask < previousEnergyLevel -> stockQuoteRequest.ask
                    else -> previousEnergyLevel
                },
        )
    }

    private fun inferEnergyLevelForNewStockByRequest(stockQuoteRequest: StockQuoteRequest) =
        StockQuoteEnergyLevel(
            isin = stockQuoteRequest.isin,
            energyLevel = stockQuoteRequest.bidOrAskIfNull,
            quoteTime = stockQuoteRequest.quoteTime,
        )
}
