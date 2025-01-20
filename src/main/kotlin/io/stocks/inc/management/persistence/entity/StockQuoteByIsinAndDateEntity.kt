package io.stocks.inc.management.persistence.entity

import org.springframework.data.cassandra.core.cql.Ordering
import org.springframework.data.cassandra.core.cql.PrimaryKeyType
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn
import org.springframework.data.cassandra.core.mapping.Table
import java.math.BigDecimal
import java.time.LocalDateTime

@Table("quotes_by_isin_and_date")
data class StockQuoteByIsinAndDateEntity(
    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED, ordinal = 0) val isin: String,
    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED, ordinal = 1) val periodId: String,
    @PrimaryKeyColumn(
        type = PrimaryKeyType.CLUSTERED,
        ordinal = 2,
        ordering = Ordering.DESCENDING,
    ) val quoteTime: LocalDateTime,
    @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED, ordinal = 3) val bid: BigDecimal,
    @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED, ordinal = 4) val ask: BigDecimal,
    val energyLevel: BigDecimal,
)
