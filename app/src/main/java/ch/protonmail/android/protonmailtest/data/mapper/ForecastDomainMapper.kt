package ch.protonmail.android.protonmailtest.data.mapper

import ch.protonmail.android.protonmailtest.data.local.entity.ForecastEntity
import ch.protonmail.android.protonmailtest.data.remote.dto.ForecastDTO
import ch.protonmail.android.protonmailtest.domain.Forecast
import javax.inject.Inject

class ForecastDomainMapper @Inject constructor() {

    fun map(localForecasts: List<ForecastEntity>): List<Forecast> =
        localForecasts.mapToDomain()

    @JvmName("mapFromRemote")
    fun map(remoteForecasts: List<ForecastDTO>): List<Forecast> =
        remoteForecasts.mapToDomain()

}

private fun List<ForecastEntity>.mapToDomain(): List<Forecast> =
    map { entity ->
        Forecast(
            day = entity.day,
            description = entity.description,
            chanceOfRain = entity.chanceOfRain,
            image = entity.image
        )
    }

@JvmName("mapFromRemote")
private fun List<ForecastDTO>.mapToDomain() =
    map { dto ->
        Forecast(
            day = dto.day.toInt(),
            description = dto.description,
            chanceOfRain = dto.chance_rain,
            image = dto.image
        )
    }
