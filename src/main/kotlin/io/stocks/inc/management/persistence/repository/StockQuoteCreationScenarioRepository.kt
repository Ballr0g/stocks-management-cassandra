package io.stocks.inc.management.persistence.repository

import io.stocks.inc.management.model.StockQuoteEnergyLevel
import io.stocks.inc.management.persistence.entity.StockQuoteByIsinAndDateEntity
import io.stocks.inc.management.persistence.entity.StockQuoteEnergyLevelEntity
import org.springframework.data.cassandra.core.CassandraOperations
import org.springframework.data.cassandra.core.query.query
import org.springframework.data.cassandra.core.query.where
import org.springframework.stereotype.Repository

@Repository
class StockQuoteCreationScenarioRepository(
    private val cassandraOperations: CassandraOperations,
) {
    fun selectEnergyLevelByIsin(isin: String): StockQuoteEnergyLevel? =
        cassandraOperations.selectOne(query(where("isin").`is`(isin)), StockQuoteEnergyLevel::class.java)

    fun batchSaveQuoteWithEnergyLevel(
        stockQuoteByIsinAndDateEntity: StockQuoteByIsinAndDateEntity,
        stockQuoteEnergyLevelEntity: StockQuoteEnergyLevelEntity,
    ) {
        TODO("Implement batch upsert for energy level-focused table and stock quote table")
    }
}
