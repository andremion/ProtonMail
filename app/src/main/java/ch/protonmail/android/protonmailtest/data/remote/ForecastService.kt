package ch.protonmail.android.protonmailtest.data.remote

import ch.protonmail.android.protonmailtest.data.remote.dto.ForecastDTO
import retrofit2.http.GET

interface ForecastService {

    @GET("api/forecast")
    suspend fun getForecasts(): List<ForecastDTO>

}
