package ch.protonmail.android.protonmailtest.data.local

import ch.protonmail.android.protonmailtest.data.local.entity.ForecastEntity
import com.nhaarman.mockitokotlin2.spy
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ForecastDAOTest {

    private val sut: ForecastDAO = spy(ForecastDAOImpl())

    @Test
    fun `save forecasts`(): Unit = runBlocking {
        val forecasts = aForecastEntityList().toTypedArray()

        sut.saveForecasts(*forecasts)

        verify(sut).saveForecasts(*forecasts)
        verify(sut).deleteAll()
        verify(sut).insert(*forecasts)
        verifyNoMoreInteractions(sut)
    }
}

private class ForecastDAOImpl : ForecastDAO() {

    override suspend fun getById(id: Int): ForecastEntity {
        TODO("no-op")
    }

    override suspend fun getAll(): List<ForecastEntity> =
        TODO("no-op")

    override suspend fun insert(vararg forecast: ForecastEntity) {
        // no-op
    }

    override suspend fun deleteAll() {
        // no-op
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
