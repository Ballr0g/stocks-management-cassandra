package io.stocks.inc.management.service.elvl.retrieval

import io.stocks.inc.management.exception.EnergyLevelNotFoundByIsinException
import io.stocks.inc.management.exception.IllegalStockIsinArgumentException
import io.stocks.inc.management.extensions.toDto
import io.stocks.inc.management.extensions.toModel
import io.stocks.inc.management.generated.model.GetEnergyLevelByIsinResponseDto
import io.stocks.inc.management.persistence.repository.EnergyLevelRetrievalScenarioRepository
import org.springframework.stereotype.Service

@Service
class EnergyLevelRetrievalService(
    private val energyLevelRetrievalScenarioRepository: EnergyLevelRetrievalScenarioRepository,
) {
    fun retrieveEnergyLevelByIsin(isin: String): GetEnergyLevelByIsinResponseDto {
        if (isin.length != 12) {
            throw IllegalStockIsinArgumentException(isin)
        }

        val energyLevelForIsin = energyLevelRetrievalScenarioRepository.selectEnergyLevelByIsin(isin)
        return energyLevelForIsin.toModel()?.toDto() ?: throw EnergyLevelNotFoundByIsinException(isin)
    }
}
