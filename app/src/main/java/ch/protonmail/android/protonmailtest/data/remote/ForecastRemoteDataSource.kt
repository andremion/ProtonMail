package ch.protonmail.android.protonmailtest.data.remote

import ch.protonmail.android.protonmailtest.data.remote.dto.ForecastDTO

class ForecastRemoteDataSource(
    private val service: ForecastService
) {

    suspend fun getForecasts(): List<ForecastDTO> =
        try {
            service.getForecasts()
        } catch (e: Throwable) {
            emptyList()
        }
}
