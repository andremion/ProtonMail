package ch.protonmail.android.protonmailtest.ui.home.forecast.model

import java.io.File

data class ForecastItemModel(
    val day: Int,
    val title: String,
    val imageFile: File
)
