package com.example.weather.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weather.data.entity.WeatherTable
import com.example.weather.data.entity.WeekForecast
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoForecast {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(users: List<WeatherTable>)

    @Query("DELETE FROM forecast_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM forecast_table LIMIT 1")
    fun getCurrentForecast(): Flow<WeatherTable>

    @Query("SELECT * FROM forecast_table LIMIT 8")
    fun getDayForecast(): Flow<List<WeatherTable>>

    @Query("SELECT MAX(`temp`) as maxT, MIN(`temp`) as minT, date as date, iconId, count(*) c FROM forecast_table WHERE dayOfMonth=:currentDay GROUP BY iconId ORDER BY c DESC LIMIT 1")
    suspend fun getWeekForecast(currentDay: Int): WeekForecast
}