package io.stocks.inc.management.persistence.repository

import io.stocks.inc.management.persistence.entity.StockQuoteEnergyLevelEntity
import org.springframework.data.cassandra.core.CassandraOperations
import org.springframework.data.cassandra.core.query.query
import org.springframework.data.cassandra.core.query.where
import org.springframework.stereotype.Repository

@Repository
class EnergyLevelRetrievalScenarioRepository(
    private val cassandraOperations: CassandraOperations,
) {
    fun selectEnergyLevelByIsin(isin: String): StockQuoteEnergyLevelEntity? =
        cassandraOperations.selectOne(query(where("isin").`is`(isin)), StockQuoteEnergyLevelEntity::class.java)
}
