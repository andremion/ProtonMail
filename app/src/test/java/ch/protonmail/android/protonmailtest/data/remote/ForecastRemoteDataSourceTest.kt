package ch.protonmail.android.protonmailtest.data.remote

import ch.protonmail.android.protonmailtest.data.remote.dto.ForecastDTO
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals

class ForecastRemoteDataSourceTest {

    private val mockService: ForecastService = mock()

    private val sut: ForecastRemoteDataSource =
        ForecastRemoteDataSource(mockService)

    @Test
    fun `get forecasts`(): Unit = runBlocking {
        val expected = listOf(
            ForecastDTO(
                day = "day",
                description = "description",
                chance_rain = 0.5f,
                image = "image"
            )
        )
        whenever(mockService.getForecasts()).thenReturn(expected)

        val actual = sut.getForecasts()

        assertEquals(expected, actual)
    }

    @Test(expected = RuntimeException::class)
    fun `get forecasts error`(): Unit = runBlocking {
        val error = RuntimeException("Error")
        whenever(mockService.getForecasts()).thenThrow(error)

        sut.getForecasts()
    }
}
