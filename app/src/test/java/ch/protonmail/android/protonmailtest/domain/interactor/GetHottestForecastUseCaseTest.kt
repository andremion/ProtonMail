package ch.protonmail.android.protonmailtest.domain.interactor

import ch.protonmail.android.protonmailtest.data.ForecastRepository
import ch.protonmail.android.protonmailtest.domain.Forecast
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals

class GetHottestForecastUseCaseTest {

    private val mockRepository: ForecastRepository = mock()

    private val sut: GetHottestForecastUseCase =
        GetHottestForecastUseCase(
            mockRepository
        )

    @Test
    fun `sort forecast by the chance of rain`(): Unit = runBlocking {
        val forecasts = anUnsortedForecastList()
        whenever(mockRepository.getForecasts()).thenReturn(forecasts)

        val actual = sut.invoke()

        val expected = aSortedForecastList()
        assertEquals(expected, actual)
    }

    @Test
    fun `filter forecast by the chance of rain less than 50 percent`(): Unit = runBlocking {
        val forecasts = anUnfilteredForecastList()
        whenever(mockRepository.getForecasts()).thenReturn(forecasts)

        val actual = sut.invoke()

        val expected = aFilteredForecastList()
        assertEquals(expected, actual)
    }
}

private fun anUnsortedForecastList(): List<Forecast> =
    listOf(
        Forecast(
            day = 1,
            description = "description",
            chanceOfRain = 0.3f,
            image = "image"
        ),
        Forecast(
            day = 2,
            description = "description",
            chanceOfRain = 0.2f,
            image = "image"
        ),
        Forecast(
            day = 3,
            description = "description",
            chanceOfRain = 0.4f,
            image = "image"
        ),
        Forecast(
            day = 4,
            description = "description",
            chanceOfRain = 0.1f,
            image = "image"
        )
    )

private fun aSortedForecastList(): List<Forecast> =
    listOf(
        Forecast(
            day = 4,
            description = "description",
            chanceOfRain = 0.1f,
            image = "image"
        ),
        Forecast(
            day = 2,
            description = "description",
            chanceOfRain = 0.2f,
            image = "image"
        ),
        Forecast(
            day = 1,
            description = "description",
            chanceOfRain = 0.3f,
            image = "image"
        ),
        Forecast(
            day = 3,
            description = "description",
            chanceOfRain = 0.4f,
            image = "image"
        )
    )

private fun anUnfilteredForecastList(): List<Forecast> =
    listOf(
        Forecast(
            day = 1,
            description = "description",
            chanceOfRain = 0.8f,
            image = "image"
        ),
        Forecast(
            day = 2,
            description = "description",
            chanceOfRain = 0.5f,
            image = "image"
        ),
        Forecast(
            day = 3,
            description = "description",
            chanceOfRain = 0.6f,
            image = "image"
        ),
        Forecast(
            day = 4,
            description = "description",
            chanceOfRain = 0.1f,
            image = "image"
        )
    )

private fun aFilteredForecastList(): List<Forecast> =
    listOf(
        Forecast(
            day = 4,
            description = "description",
            chanceOfRain = 0.1f,
            image = "image"
        )
    )
