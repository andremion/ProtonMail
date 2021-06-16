package ch.protonmail.android.protonmailtest.ui.detail.model

import java.io.File

data class ForecastDetailsModel(
    val day: Int,
    val title: String,
    val description: String,
    val chanceOfRain: String,
    val imageUri: String,
    val imageFile: File,
    val showDownloadButton: Boolean
)
