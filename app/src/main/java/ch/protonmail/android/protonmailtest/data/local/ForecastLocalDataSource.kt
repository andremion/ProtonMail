package ch.protonmail.android.protonmailtest.data.local

import ch.protonmail.android.protonmailtest.data.local.entity.ForecastEntity

class ForecastLocalDataSource(
    private val dao: ForecastDAO
) {

    suspend fun getForecasts(): List<ForecastEntity> =
        dao.getAll()

    suspend fun saveForecasts(forecasts: List<ForecastEntity>) {
        dao.saveForecasts(*forecasts.toTypedArray())
    }
}
