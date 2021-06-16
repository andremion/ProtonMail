package ch.protonmail.android.protonmailtest.ui.detail.mapper

import android.content.Context
import ch.protonmail.android.protonmailtest.R
import ch.protonmail.android.protonmailtest.domain.Forecast
import ch.protonmail.android.protonmailtest.ui.detail.model.ForecastDetailsModel
import ch.protonmail.android.protonmailtest.ui.util.imageFile
import java.text.NumberFormat
import javax.inject.Inject

class ForecastDetailsModelMapper @Inject constructor(
    private val context: Context
) {

    fun map(forecast: Forecast): ForecastDetailsModel =
        forecast.mapToModel()

    private fun Forecast.mapToModel(): ForecastDetailsModel =
        ForecastDetailsModel(
            day = day,
            title = context.getString(R.string.details_title, day),
            description = description,
            chanceOfRain = chanceOfRain.asPercent(),
            imageUri = image,
            imageFile = context.imageFile(day),
            showDownloadButton = !context.imageFile(day).exists()
        )

    private fun Float.asPercent(): String =
        NumberFormat.getPercentInstance().format(this)
}
