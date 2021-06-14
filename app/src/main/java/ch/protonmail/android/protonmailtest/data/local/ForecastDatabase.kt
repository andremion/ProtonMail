package ch.protonmail.android.protonmailtest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ch.protonmail.android.protonmailtest.data.local.entity.ForecastEntity

@Database(entities = [ForecastEntity::class], version = 1)
abstract class ForecastDatabase : RoomDatabase() {
    abstract fun forecastDAO(): ForecastDAO
}
