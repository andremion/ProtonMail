package ch.protonmail.android.protonmailtest.data.remote

import ch.protonmail.android.protonmailtest.data.remote.dto.ForecastDTO

class ForecastRemoteDataSource(
    private val service: ForecastService
) {

    suspend fun getForecasts(): List<ForecastDTO> =
        service.getForecasts()
}
