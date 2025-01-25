package io.stocks.inc.management.exception

open class IllegalPropertyArgumentException(
    val propertyName: String,
    open val actualValue: Any,
    override val message: String,
) : IllegalArgumentException(message)

class IllegalStockQuoteEnergyLevelArgumentException(
    actualValue: Any,
    message: String,
) : IllegalPropertyArgumentException(propertyName = "isin", actualValue = actualValue, message = message)

class IllegalStockQuoteEntryArgumentException(
    propertyName: String,
    actualValue: Any,
    message: String,
) : IllegalPropertyArgumentException(propertyName, actualValue, message)

class IllegalStockQuoteRequestArgumentException(
    propertyName: String,
    actualValue: Any,
    message: String,
) : IllegalPropertyArgumentException(propertyName, actualValue, message)

class IllegalStockIsinArgumentException(
    override val actualValue: String,
) : IllegalPropertyArgumentException("isin", actualValue, "isin must be 12 chars, got: $actualValue (${actualValue.length} chars)")
