package ch.protonmail.android.protonmailtest.data.local.di

import android.content.Context
import androidx.room.Room
import ch.protonmail.android.protonmailtest.data.local.ForecastDAO
import ch.protonmail.android.protonmailtest.data.local.ForecastDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

private const val FORECAST_DATABASE_NAME = "forecast-db"

@Module
object LocalModule {

    @Provides
    @Singleton
    fun providesDatabase(context: Context): ForecastDatabase =
        Room.databaseBuilder(context, ForecastDatabase::class.java, FORECAST_DATABASE_NAME).build()

    @Provides
    @Singleton
    fun providesForecastDAO(db: ForecastDatabase): ForecastDAO =
        db.forecastDAO()
}
