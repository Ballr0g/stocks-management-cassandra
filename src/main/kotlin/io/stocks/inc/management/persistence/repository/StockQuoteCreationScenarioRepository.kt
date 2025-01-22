package io.stocks.inc.management.persistence.repository

import io.stocks.inc.management.persistence.entity.StockQuoteByIsinAndDateEntity
import io.stocks.inc.management.persistence.entity.StockQuoteEnergyLevelEntity
import org.springframework.data.cassandra.core.CassandraOperations
import org.springframework.data.cassandra.core.WriteResult
import org.springframework.data.cassandra.core.query.query
import org.springframework.data.cassandra.core.query.where
import org.springframework.stereotype.Repository

@Repository
class StockQuoteCreationScenarioRepository(
    private val cassandraOperations: CassandraOperations,
) {
    fun selectEnergyLevelByIsin(isin: String): StockQuoteEnergyLevelEntity? =
        cassandraOperations.selectOne(query(where("isin").`is`(isin)), StockQuoteEnergyLevelEntity::class.java)

    fun batchSaveQuoteWithEnergyLevel(
        stockQuoteByIsinAndDateEntity: StockQuoteByIsinAndDateEntity,
        stockQuoteEnergyLevelEntity: StockQuoteEnergyLevelEntity,
    ): WriteResult {
        val batchOperations = cassandraOperations.batchOps()
        batchOperations.insert(stockQuoteByIsinAndDateEntity, stockQuoteEnergyLevelEntity)
        return batchOperations.execute()
    }
}
