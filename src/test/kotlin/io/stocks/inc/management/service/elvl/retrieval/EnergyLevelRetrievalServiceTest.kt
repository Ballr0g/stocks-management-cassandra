package io.stocks.inc.management.service.elvl.retrieval

import io.stocks.inc.management.exception.EnergyLevelNotFoundByIsinException
import io.stocks.inc.management.exception.IllegalStockIsinArgumentException
import io.stocks.inc.management.model.PrimitiveStockQuoteFixtures
import io.stocks.inc.management.model.dto.PostQuoteDtoFixtures
import io.stocks.inc.management.model.entity.PostQuoteEntityFixtures
import io.stocks.inc.management.persistence.repository.EnergyLevelRetrievalScenarioRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class EnergyLevelRetrievalServiceTest {
    @Mock
    private lateinit var energyLevelRetrievalScenarioRepository: EnergyLevelRetrievalScenarioRepository

    @InjectMocks
    private lateinit var energyLevelRetrievalService: EnergyLevelRetrievalService

    @Test
    fun `when isin invalid IllegalStockIsinArgumentException is thrown`() {
        // given
        // when
        // then
        assertThrows<IllegalStockIsinArgumentException> {
            energyLevelRetrievalService.retrieveEnergyLevelByIsin(PrimitiveStockQuoteFixtures.TEST_INVALID_ISIN_TOO_LONG)
        }
    }

    @Test
    fun `when energy level doesn't exist for isin EnergyLevelNotFoundByIsinException is thrown`() {
        // given
        `when`(energyLevelRetrievalScenarioRepository.selectEnergyLevelByIsin(anyString())).thenReturn(null)

        // when
        // then
        assertThrows<EnergyLevelNotFoundByIsinException> {
            energyLevelRetrievalService.retrieveEnergyLevelByIsin(PrimitiveStockQuoteFixtures.TEST_VALID_ISIN)
        }
    }

    @Test
    fun `when energy level exists for isin valid GetEnergyLevelByIsinResponseDto is returned`() {
        // given
        `when`(energyLevelRetrievalScenarioRepository.selectEnergyLevelByIsin(anyString())).thenReturn(
            PostQuoteEntityFixtures.TEST_STOCK_QUOTE_ENERGY_LEVEL_ENTITY,
        )

        // when
        val actualEnergyLevelDto = energyLevelRetrievalService.retrieveEnergyLevelByIsin(PrimitiveStockQuoteFixtures.TEST_VALID_ISIN)

        // then
        assertThat(actualEnergyLevelDto)
            .isEqualTo(PostQuoteDtoFixtures.TEST_GET_ENERGY_LEVEL_BY_ISIN_RESPONSE_DTO)
    }
}
