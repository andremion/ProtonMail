package ch.protonmail.android.protonmailtest.domain.interactor

import ch.protonmail.android.protonmailtest.data.ForecastRepository
import ch.protonmail.android.protonmailtest.domain.Forecast
import javax.inject.Inject

class GetHottestForecastUseCase @Inject constructor(
    private val repository: ForecastRepository
) : GetForecastUseCase {

    override suspend operator fun invoke(): List<Forecast> =
        repository.getForecasts()
            .filter { forecast -> forecast.chanceOfRain < 0.5f }
            .sortedBy(Forecast::chanceOfRain)
}
