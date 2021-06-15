package ch.protonmail.android.protonmailtest.domain.interactor

import ch.protonmail.android.protonmailtest.data.ForecastRepository
import ch.protonmail.android.protonmailtest.domain.Forecast

class GetUpcomingForecastUseCase(
    private val repository: ForecastRepository
) {

    suspend operator fun invoke(): List<Forecast> =
        repository.getForecasts()
            .sortedBy(Forecast::day)
}
