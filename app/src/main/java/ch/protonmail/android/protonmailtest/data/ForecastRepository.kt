package ch.protonmail.android.protonmailtest.data

import ch.protonmail.android.protonmailtest.data.local.ForecastLocalDataSource
import ch.protonmail.android.protonmailtest.data.mapper.ForecastDomainMapper
import ch.protonmail.android.protonmailtest.data.mapper.ForecastLocalMapper
import ch.protonmail.android.protonmailtest.data.remote.ForecastRemoteDataSource
import ch.protonmail.android.protonmailtest.domain.Forecast
import javax.inject.Inject

class ForecastRepository @Inject constructor(
    private val localDataSource: ForecastLocalDataSource,
    private val remoteDataSource: ForecastRemoteDataSource,
    private val domainMapper: ForecastDomainMapper,
    private val localMapper: ForecastLocalMapper
) {

    suspend fun getForecasts(): List<Forecast> {
        val remoteForecasts = remoteDataSource.getForecasts()
        return if (remoteForecasts.isEmpty()) {
            val localForecasts = localDataSource.getForecasts()
            domainMapper.map(localForecasts)
        } else {
            val localForecasts = localMapper.map(remoteForecasts)
            localDataSource.saveForecasts(localForecasts)
            domainMapper.map(remoteForecasts)
        }
    }
}
