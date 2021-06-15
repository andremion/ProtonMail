package ch.protonmail.android.protonmailtest.data.mapper

import ch.protonmail.android.protonmailtest.data.local.entity.ForecastEntity
import ch.protonmail.android.protonmailtest.data.remote.dto.ForecastDTO
import org.junit.Test
import kotlin.test.assertEquals

class ForecastLocalMapperTest {

    private val sut: ForecastLocalMapper =
        ForecastLocalMapper()

    @Test
    fun map() {
        val remoteForecasts = aRemoteForecastList()

        val actual = sut.map(remoteForecasts)

        val expected = aLocalForecastList()
        assertEquals(expected, actual)
    }
}

private fun aRemoteForecastList(): List<ForecastDTO> =
    listOf(
        ForecastDTO(
            day = "1",
            description = "description",
            chance_rain = 0.5f,
            image = "image"
        )
    )

private fun aLocalForecastList(): List<ForecastEntity> =
    listOf(
        ForecastEntity(
            day = 1,
            description = "description",
            chanceOfRain = 0.5f,
            image = "image"
        )
    )
