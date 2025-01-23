package io.stocks.inc.management.service

import io.stocks.inc.management.model.StockQuoteEntry
import io.stocks.inc.management.model.StockQuoteRequest
import org.springframework.stereotype.Component

@Component
class StockQuoteSavingHandler(
    private val stockEnergyLevelRetriever: StockEnergyLevelRetriever,
    private val stockQuoteCreationBatchSaver: StockQuoteCreationBatchSaver,
) {
    fun addStockQuoteEntry(stockQuoteEntry: StockQuoteRequest): StockQuoteEntry {
        val newEnergyLevelEntry = stockEnergyLevelRetriever.calculateNewEnergyLevelForStockQuoteRequest(stockQuoteEntry)
        return stockQuoteCreationBatchSaver.batchSaveNewStockQuoteEntry(stockQuoteEntry, newEnergyLevelEntry)
    }
}
