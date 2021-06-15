package ch.protonmail.android.protonmailtest.domain.interactor

import ch.protonmail.android.protonmailtest.domain.Forecast

interface GetForecastUseCase {

    suspend operator fun invoke(): List<Forecast>
}
