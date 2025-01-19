package io.stocks.inc.management

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StocksManagementApplication

fun main(args: Array<String>) {
    runApplication<StocksManagementApplication>(*args)
}
