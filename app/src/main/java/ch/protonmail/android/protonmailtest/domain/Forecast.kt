package ch.protonmail.android.protonmailtest.domain

data class Forecast(
    val day: Int,
    val description: String,
    val chanceOfRain: Float,
    val image: String
)
