package io.stocks.inc.management.persistence.entity

import org.springframework.data.cassandra.core.cql.Ordering
import org.springframework.data.cassandra.core.cql.PrimaryKeyType
import org.springframework.data.cassandra.core.mapping.Column
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn
import org.springframework.data.cassandra.core.mapping.Table
import java.math.BigDecimal
import java.time.LocalDateTime

@Table(keyspace = "stocks", value = "quotes_by_isin_and_date")
data class StockQuoteByIsinAndDateEntity(
    @field:PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED, ordinal = 0) val isin: String,
    @field:PrimaryKeyColumn(
        type = PrimaryKeyType.PARTITIONED,
        name = "period_id",
        ordinal = 1,
    ) val periodId: String,
    @field:PrimaryKeyColumn(
        type = PrimaryKeyType.CLUSTERED,
        name = "quote_time",
        ordinal = 2,
        ordering = Ordering.DESCENDING,
    ) val quoteTime: LocalDateTime,
    @field:PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED, ordinal = 3) val bid: BigDecimal,
    @field:PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED, ordinal = 4) val ask: BigDecimal,
    @field:Column("energy_level") val energyLevel: BigDecimal,
)
