package ch.protonmail.android.protonmailtest.domain.interactor

import ch.protonmail.android.protonmailtest.data.ForecastRepository
import ch.protonmail.android.protonmailtest.domain.Forecast
import javax.inject.Inject

class GetForecastDetailsUseCase @Inject constructor(
    private val repository: ForecastRepository
) {

    suspend operator fun invoke(id: Int): Forecast = repository.getForecast(id)
}
