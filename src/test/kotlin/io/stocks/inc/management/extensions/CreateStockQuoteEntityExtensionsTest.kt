package io.stocks.inc.management.extensions

import io.stocks.inc.management.model.StockQuoteEnergyLevelFixtures
import io.stocks.inc.management.model.StockQuoteRequestFixtures
import io.stocks.inc.management.model.entity.PostQuoteEntityFixtures
import io.stocks.inc.management.persistence.entity.StockQuoteEnergyLevelEntity
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CreateStockQuoteEntityExtensionsTest {
    @Test
    fun `when StockQuoteRequest mapped to StockQuoteByIsinAndDateEntity all properties match`() {
        // given
        // when
        val actualMappedStockQuoteByIsinAndDateEntity =
            StockQuoteRequestFixtures.TEST_STOCK_QUOTE_REQUEST
                .toEntityWithEnergyLevel(StockQuoteEnergyLevelFixtures.TEST_STOCK_QUOTE_ENERGY_LEVEL)

        // then
        assertThat(actualMappedStockQuoteByIsinAndDateEntity)
            .isEqualTo(PostQuoteEntityFixtures.TEST_VALID_STOCK_QUOTE_BY_ISIN_AND_DATE_ENTITY)
    }

    @Test
    fun `when StockQuoteEnergyLevel mapped to StockQuoteEnergyLevelEntity all properties match`() {
        // given
        // when
        val actualMappedStockQuoteEnergyLevelEntity = StockQuoteEnergyLevelFixtures.TEST_STOCK_QUOTE_ENERGY_LEVEL.toEntity()

        // then
        assertThat(actualMappedStockQuoteEnergyLevelEntity)
            .isEqualTo(PostQuoteEntityFixtures.TEST_STOCK_QUOTE_ENERGY_LEVEL_ENTITY)
    }

    @Test
    fun `when StockQuoteEnergyLevelEntity null mapped null is returned`() {
        // given
        val nullStockQuoteEnergyLevelEntity: StockQuoteEnergyLevelEntity? = null

        // when
        // then
        assertThat(nullStockQuoteEnergyLevelEntity.toModel())
            .isNull()
    }

    @Test
    fun `when StockQuoteEnergyLevelEntity mapped to StockQuoteEnergyLevel all properties match`() {
        // given
        // when
        val actualMappedStockQuoteEnergyLevel = PostQuoteEntityFixtures.TEST_STOCK_QUOTE_ENERGY_LEVEL_ENTITY.toModel()

        // then
        assertThat(actualMappedStockQuoteEnergyLevel)
            .isNotNull()
            .isEqualTo(StockQuoteEnergyLevelFixtures.TEST_STOCK_QUOTE_ENERGY_LEVEL)
    }
}
