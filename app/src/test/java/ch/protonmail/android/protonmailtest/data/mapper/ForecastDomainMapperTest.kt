package ch.protonmail.android.protonmailtest.data.mapper

import ch.protonmail.android.protonmailtest.data.local.entity.ForecastEntity
import ch.protonmail.android.protonmailtest.data.remote.dto.ForecastDTO
import ch.protonmail.android.protonmailtest.domain.Forecast
import org.junit.Test
import kotlin.test.assertEquals

class ForecastDomainMapperTest {

    private val sut: ForecastDomainMapper =
        ForecastDomainMapper()

    @Test
    fun `map from local`() {
        val localForecasts = aLocalForecastList()

        val actual = sut.map(localForecasts)

        val expected = aForecastList()
        assertEquals(expected, actual)
    }

    @Test
    fun `map from remote`() {
        val remoteForecasts = aRemoteForecastList()

        val actual = sut.map(remoteForecasts)

        val expected = aForecastList()
        assertEquals(expected, actual)
    }
}

private fun aLocalForecastList(): List<ForecastEntity> =
    listOf(
        ForecastEntity(
            day = 1,
            description = "description",
            chanceOfRain = 0.5f,
            image = "image"
        )
    )

private fun aRemoteForecastList(): List<ForecastDTO> =
    listOf(
        ForecastDTO(
            day = "1",
            description = "description",
            chance_rain = 0.5f,
            image = "image"
        )
    )

private fun aForecastList(): List<Forecast> =
    listOf(
        Forecast(
            day = 1,
            description = "description",
            chanceOfRain = 0.5f,
            image = "image"
        )
    )
