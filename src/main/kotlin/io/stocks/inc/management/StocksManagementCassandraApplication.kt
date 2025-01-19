package io.stocks.inc.management

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StocksManagementCassandraApplication

fun main(args: Array<String>) {
    runApplication<StocksManagementCassandraApplication>(*args)
}
