package ch.protonmail.android.protonmailtest.ui.home.forecast.mapper

import android.content.Context
import ch.protonmail.android.protonmailtest.R
import ch.protonmail.android.protonmailtest.domain.Forecast
import ch.protonmail.android.protonmailtest.ui.home.forecast.model.ForecastItemModel
import ch.protonmail.android.protonmailtest.ui.util.imageFile
import javax.inject.Inject

class ForecastItemModelMapper @Inject constructor(
    private val context: Context
) {

    fun map(forecasts: List<Forecast>): List<ForecastItemModel> =
        forecasts.mapToModel()

    private fun List<Forecast>.mapToModel(): List<ForecastItemModel> =
        map { forecast -> forecast.mapToModel() }

    private fun Forecast.mapToModel(): ForecastItemModel =
        ForecastItemModel(
            day = day,
            title = context.getString(R.string.home_forecast_item_title, day, description),
            imageFile = context.imageFile(day)
        )
}
