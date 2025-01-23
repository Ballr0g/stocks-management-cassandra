package io.stocks.inc.management.model

import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object PrimitiveStockQuoteFixtures {
    private val DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    const val TEST_VALID_ISIN = "RU000A0JX0J2"
    const val TEST_INVALID_ISIN_TOO_LONG = "RU000A0JX0J2KEK"
    const val TEST_INVALID_ISIN_TOO_SHORT = "RU000A0JX"
    const val TEST_PERIOD_ID = "2025-01-20"
    val TEST_VALID_BID = BigDecimal(100.2.toString())
    val TEST_VALID_ASK = BigDecimal(101.9.toString())
    val TEST_ASK_EQUAL_TO_BID = TEST_VALID_BID
    val TEST_ASK_LESS_THAN_BID = TEST_VALID_BID - BigDecimal.ONE
    val TEST_VALID_ENERGY_LEVEL = TEST_VALID_BID
    val TEST_ENERGY_LEVEL_UPDATE_TIME: LocalDateTime = LocalDateTime.parse("2025-01-20 12:00:00", DATE_FORMATTER)
}
