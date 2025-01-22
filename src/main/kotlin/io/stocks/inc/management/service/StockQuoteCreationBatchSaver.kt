package io.stocks.inc.management.service

import io.stocks.inc.management.extensions.toEntity
import io.stocks.inc.management.extensions.toEntityWithEnergyLevel
import io.stocks.inc.management.model.StockQuoteEnergyLevel
import io.stocks.inc.management.model.StockQuoteEntry
import io.stocks.inc.management.model.StockQuoteRequest
import io.stocks.inc.management.persistence.repository.StockQuoteCreationScenarioRepository
import org.springframework.stereotype.Component

@Component
class StockQuoteCreationBatchSaver(
    private val stockQuoteCreationScenarioRepository: StockQuoteCreationScenarioRepository,
) {
    fun batchSaveNewStockQuoteEntry(
        stockQuoteRequest: StockQuoteRequest,
        stockQuoteEnergyLevel: StockQuoteEnergyLevel,
    ): StockQuoteEntry {
        val stockQuoteSavedEntity = stockQuoteRequest.toEntityWithEnergyLevel(stockQuoteEnergyLevel)
        val stockQuoteSavedEnergyLevelEntity = stockQuoteEnergyLevel.toEntity()

        val batchWriteResult =
            stockQuoteCreationScenarioRepository.batchSaveQuoteWithEnergyLevel(
                stockQuoteSavedEntity,
                stockQuoteSavedEnergyLevelEntity,
            )
        check (batchWriteResult.wasApplied()) { "Cassandra batch save failure" }

        return StockQuoteEntry(
            isin = stockQuoteSavedEntity.isin,
            bid = stockQuoteSavedEntity.bid,
            ask = stockQuoteSavedEntity.ask,
            energyLevel = stockQuoteSavedEnergyLevelEntity.energyLevel,
        )
    }
}
