package io.stocks.inc.management.exception

open class NotFoundException(
    val missingIdName: String,
    override val message: String,
) : RuntimeException(message)

class EnergyLevelNotFoundByIsinException(
    val isinForMissingEnergyLevel: String,
) : NotFoundException("isin", "Energy level not found for isin: $isinForMissingEnergyLevel")
