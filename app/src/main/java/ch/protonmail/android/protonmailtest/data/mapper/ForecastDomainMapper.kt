package ch.protonmail.android.protonmailtest.data.mapper

import ch.protonmail.android.protonmailtest.data.local.entity.ForecastEntity
import ch.protonmail.android.protonmailtest.data.remote.dto.ForecastDTO
import ch.protonmail.android.protonmailtest.domain.Forecast
import javax.inject.Inject

class ForecastDomainMapper @Inject constructor() {

    fun map(localForecasts: List<ForecastEntity>): List<Forecast> =
        localForecasts.mapToDomain()

    fun map(localForecast: ForecastEntity): Forecast =
        localForecast.mapToDomain()

    @JvmName("mapFromRemote")
    fun map(remoteForecasts: List<ForecastDTO>): List<Forecast> =
        remoteForecasts.mapToDomain()

}

private fun List<ForecastEntity>.mapToDomain(): List<Forecast> =
    map(ForecastEntity::mapToDomain)

private fun ForecastEntity.mapToDomain(): Forecast =
    Forecast(
        day = day,
        description = description,
        chanceOfRain = chanceOfRain,
        image = image
    )

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
