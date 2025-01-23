package io.stocks.inc.management.persistence.entity

import org.springframework.data.cassandra.core.mapping.Column
import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table
import java.math.BigDecimal
import java.time.LocalDateTime

@Table("energy_level_by_isin")
data class StockQuoteEnergyLevelEntity(
    @field:PrimaryKey val isin: String,
    @field:Column("energy_level") val energyLevel: BigDecimal,
    @field:Column("quote_time") val quoteTime: LocalDateTime,
)
