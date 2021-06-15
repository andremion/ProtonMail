package ch.protonmail.android.protonmailtest.domain.interactor

import ch.protonmail.android.protonmailtest.data.ForecastRepository
import ch.protonmail.android.protonmailtest.domain.Forecast

class GetUpcomingForecastUseCase constructor(
    private val repository: ForecastRepository
) : GetForecastUseCase {

    override suspend operator fun invoke(): List<Forecast> =
        repository.getForecasts()
            .sortedBy(Forecast::day)
}
