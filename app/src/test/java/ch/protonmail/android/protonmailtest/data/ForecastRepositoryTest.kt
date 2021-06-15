package ch.protonmail.android.protonmailtest.data

import ch.protonmail.android.protonmailtest.data.local.ForecastLocalDataSource
import ch.protonmail.android.protonmailtest.data.local.entity.ForecastEntity
import ch.protonmail.android.protonmailtest.data.mapper.ForecastDomainMapper
import ch.protonmail.android.protonmailtest.data.mapper.ForecastLocalMapper
import ch.protonmail.android.protonmailtest.data.remote.ForecastRemoteDataSource
import ch.protonmail.android.protonmailtest.data.remote.dto.ForecastDTO
import ch.protonmail.android.protonmailtest.domain.Forecast
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals

class ForecastRepositoryTest {

    private val mockLocalDataSource: ForecastLocalDataSource = mock()
    private val mockRemoteDataSource: ForecastRemoteDataSource = mock()
    private val mockDomainMapper: ForecastDomainMapper = mock()
    private val mockLocalMapper: ForecastLocalMapper = mock()

    private val sut: ForecastRepository =
        ForecastRepository(
            mockLocalDataSource,
            mockRemoteDataSource,
            mockDomainMapper,
            mockLocalMapper
        )

    @Test
    fun `fallback to local data source when remote data source is empty`(): Unit = runBlocking {
        whenever(mockRemoteDataSource.getForecasts()).thenReturn(emptyList())
        val localForecasts = aLocalForecastList()
        whenever(mockLocalDataSource.getForecasts()).thenReturn(localForecasts)
        val expected = aForecastList()
        whenever(mockDomainMapper.map(localForecasts)).thenReturn(expected)

        val actual = sut.getForecasts()

        assertEquals(expected, actual)
    }

    @Test
    fun `get forecasts when remote data source is not empty and save them locally`(): Unit = runBlocking {
        val remoteForecasts = aRemoteForecastList()
        whenever(mockRemoteDataSource.getForecasts()).thenReturn(remoteForecasts)
        val expected = aForecastList()
        whenever(mockDomainMapper.map(remoteForecasts)).thenReturn(expected)
        val localForecasts = aLocalForecastList()
        whenever(mockLocalMapper.map(remoteForecasts)).thenReturn(localForecasts)

        val actual = sut.getForecasts()

        assertEquals(expected, actual)
        verify(mockLocalDataSource).saveForecasts(localForecasts)
        verifyNoMoreInteractions(mockLocalDataSource)
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
