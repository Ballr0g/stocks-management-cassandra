package io.stocks.inc.management.service.quote.creation

import io.stocks.inc.management.model.PrimitiveStockQuoteFixtures
import io.stocks.inc.management.model.StockQuoteEnergyLevelFixtures
import io.stocks.inc.management.model.StockQuoteRequestFixtures
import io.stocks.inc.management.model.entity.PostQuoteEntityFixtures
import io.stocks.inc.management.persistence.repository.StockQuoteCreationScenarioRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.anyString
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import java.math.BigDecimal

@ExtendWith(MockitoExtension::class)
class StockEnergyLevelRetrieverTest {
    @Mock
    private lateinit var stockQuoteCreationScenarioRepository: StockQuoteCreationScenarioRepository

    @InjectMocks
    private lateinit var stockEnergyLevelRetriever: StockEnergyLevelRetriever

    @Test
    fun `when no previous energy level present should return a new entry with bid as energy level`() {
        // given
        `when`(stockQuoteCreationScenarioRepository.selectEnergyLevelByIsin(anyString())).thenReturn(null)

        // when
        val actualNewEnergyLevel =
            stockEnergyLevelRetriever.calculateNewEnergyLevelForStockQuoteRequest(
                StockQuoteRequestFixtures.TEST_STOCK_QUOTE_REQUEST,
            )

        // then
        assertThat(actualNewEnergyLevel)
            .isEqualTo(StockQuoteEnergyLevelFixtures.TEST_STOCK_QUOTE_ENERGY_LEVEL)
    }

    @Test
    fun `when no previous energy level present and bid absent should return a new entry with ask as energy level`() {
        // given
        `when`(stockQuoteCreationScenarioRepository.selectEnergyLevelByIsin(anyString())).thenReturn(null)
        val testStockQuoteRequestNoBid = StockQuoteRequestFixtures.TEST_STOCK_QUOTE_REQUEST.copy(bid = null)
        val expectedEnergyLevel =
            StockQuoteEnergyLevelFixtures.TEST_STOCK_QUOTE_ENERGY_LEVEL.copy(
                energyLevel = PrimitiveStockQuoteFixtures.TEST_VALID_ASK,
            )

        // when
        val actualNewEnergyLevel = stockEnergyLevelRetriever.calculateNewEnergyLevelForStockQuoteRequest(testStockQuoteRequestNoBid)

        // then
        assertThat(actualNewEnergyLevel)
            .isEqualTo(expectedEnergyLevel)
    }

    @Test
    fun `when previous energy level less than new bid should return new bid as energy level`() {
        // given
        `when`(
            stockQuoteCreationScenarioRepository.selectEnergyLevelByIsin(anyString()),
        ).thenReturn(PostQuoteEntityFixtures.TEST_STOCK_QUOTE_ENERGY_LEVEL_ENTITY)
        val newHigherBid = PrimitiveStockQuoteFixtures.TEST_VALID_BID + BigDecimal.ONE
        val testStockQuoteRequestHigherBid = StockQuoteRequestFixtures.TEST_STOCK_QUOTE_REQUEST.copy(bid = newHigherBid)
        val expectedEnergyLevelHigherBid = StockQuoteEnergyLevelFixtures.TEST_STOCK_QUOTE_ENERGY_LEVEL.copy(energyLevel = newHigherBid)

        // when
        val actualNewEnergyLevel = stockEnergyLevelRetriever.calculateNewEnergyLevelForStockQuoteRequest(testStockQuoteRequestHigherBid)

        // then
        assertThat(actualNewEnergyLevel)
            .isEqualTo(expectedEnergyLevelHigherBid)
    }

    @Test
    fun `when previous energy level greater than new ask should return new ask as energy level`() {
        // given
        `when`(
            stockQuoteCreationScenarioRepository.selectEnergyLevelByIsin(anyString()),
        ).thenReturn(PostQuoteEntityFixtures.TEST_STOCK_QUOTE_ENERGY_LEVEL_ENTITY)
        val newLowerAsk = PrimitiveStockQuoteFixtures.TEST_VALID_ASK - BigDecimal.ONE
        val testStockQuoteRequestLowerAsk = StockQuoteRequestFixtures.TEST_STOCK_QUOTE_REQUEST.copy(bid = newLowerAsk)
        val expectedEnergyLevelLowerAsk = StockQuoteEnergyLevelFixtures.TEST_STOCK_QUOTE_ENERGY_LEVEL.copy(energyLevel = newLowerAsk)

        // when
        val actualNewEnergyLevel = stockEnergyLevelRetriever.calculateNewEnergyLevelForStockQuoteRequest(testStockQuoteRequestLowerAsk)

        // then
        assertThat(actualNewEnergyLevel)
            .isEqualTo(expectedEnergyLevelLowerAsk)
    }

    @Test
    fun `when previous energy level same as new value should return previous energy level`() {
        // given
        `when`(
            stockQuoteCreationScenarioRepository.selectEnergyLevelByIsin(anyString()),
        ).thenReturn(PostQuoteEntityFixtures.TEST_STOCK_QUOTE_ENERGY_LEVEL_ENTITY)

        // when
        val actualNewEnergyLevel =
            stockEnergyLevelRetriever.calculateNewEnergyLevelForStockQuoteRequest(
                StockQuoteRequestFixtures.TEST_STOCK_QUOTE_REQUEST,
            )

        // then
        assertThat(actualNewEnergyLevel)
            .isEqualTo(StockQuoteEnergyLevelFixtures.TEST_STOCK_QUOTE_ENERGY_LEVEL)
    }
}
