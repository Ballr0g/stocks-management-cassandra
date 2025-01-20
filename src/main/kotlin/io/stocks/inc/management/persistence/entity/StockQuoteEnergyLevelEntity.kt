package io.stocks.inc.management.persistence.entity

import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table
import java.math.BigDecimal
import java.time.LocalDateTime

@Table("energy_level_by_isin")
data class StockQuoteEnergyLevelEntity(
    @PrimaryKey val isin: String,
    val energyLevel: BigDecimal,
    val quoteTime: LocalDateTime,
)
