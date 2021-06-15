package ch.protonmail.android.protonmailtest.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ForecastDTO(
    val day: String,
    val description: String,
    val chance_rain: Float,
    val image: String
)
