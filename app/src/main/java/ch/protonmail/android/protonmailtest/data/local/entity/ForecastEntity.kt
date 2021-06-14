package ch.protonmail.android.protonmailtest.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "forecasts")
data class ForecastEntity(
    @PrimaryKey val day: Int,
    val description: String,
    val chanceOfRain: Float,
    val image: String
)
