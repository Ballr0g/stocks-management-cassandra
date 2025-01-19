package io.stocks.inc.management.model

fun interface PeriodIdProvider {
    fun currentPeriodId(): String
}
