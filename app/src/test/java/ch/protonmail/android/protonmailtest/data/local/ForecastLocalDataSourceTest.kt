package ch.protonmail.android.protonmailtest.data.local

import ch.protonmail.android.protonmailtest.data.local.entity.ForecastEntity
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals

class ForecastLocalDataSourceTest {

    private val mockDao: ForecastDAO = mock()

    private val sut: ForecastLocalDataSource =
        ForecastLocalDataSource(mockDao)

    @Test
    fun `get forecasts`(): Unit = runBlocking {
        val expected = aForecastEntityList()
        whenever(mockDao.getAll()).thenReturn(expected)

        val actual = sut.getForecasts()

        assertEquals(expected, actual)
    }

    @Test(expected = RuntimeException::class)
    fun `get forecasts error`(): Unit = runBlocking {
        val error = RuntimeException("Error")
        whenever(mockDao.getAll()).thenThrow(error)

        sut.getForecasts()
    }

    @Test
    fun `save forecasts`(): Unit = runBlocking {
        val forecasts = aForecastEntityList()

        sut.saveForecasts(forecasts)

        verify(mockDao).saveForecasts(*forecasts.toTypedArray())
        verifyNoMoreInteractions(mockDao)
    }

    @Test(expected = RuntimeException::class)
    fun `save forecasts error`(): Unit = runBlocking {
        val forecasts = aForecastEntityList()
        val error = RuntimeException("Error")
        whenever(mockDao.saveForecasts(*forecasts.toTypedArray())).thenThrow(error)

        sut.saveForecasts(forecasts)
    }

}

private fun aForecastEntityList(): List<ForecastEntity> =
    listOf(
        ForecastEntity(
            day = 1,
            description = "description",
            chanceOfRain = 0.5f,
            image = "image"
        )
    )
