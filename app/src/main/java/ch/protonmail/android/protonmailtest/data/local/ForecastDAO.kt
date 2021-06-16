package ch.protonmail.android.protonmailtest.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import ch.protonmail.android.protonmailtest.data.local.entity.ForecastEntity

@Dao
abstract class ForecastDAO {

    @Query("SELECT * FROM forecasts WHERE day = :day")
    abstract suspend fun getByDay(day: Int): ForecastEntity

    @Query("SELECT * FROM forecasts")
    abstract suspend fun getAll(): List<ForecastEntity>

    @Insert(entity = ForecastEntity::class)
    abstract suspend fun insert(vararg forecast: ForecastEntity)

    @Query("DELETE FROM forecasts")
    abstract suspend fun deleteAll()

    @Transaction
    open suspend fun saveForecasts(vararg forecast: ForecastEntity) {
        deleteAll()
        insert(*forecast)
    }
}
