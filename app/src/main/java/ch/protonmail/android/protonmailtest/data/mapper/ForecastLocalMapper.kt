package ch.protonmail.android.protonmailtest.data.mapper

import ch.protonmail.android.protonmailtest.data.local.entity.ForecastEntity
import ch.protonmail.android.protonmailtest.data.remote.dto.ForecastDTO

class ForecastLocalMapper {

    fun map(remoteForecasts: List<ForecastDTO>): List<ForecastEntity> =
        remoteForecasts.mapToLocal()
}

private fun List<ForecastDTO>.mapToLocal() =
    map { dto ->
        ForecastEntity(
            day = dto.day.toInt(),
            description = dto.description,
            chanceOfRain = dto.chance_rain,
            image = dto.image
        )
    }
