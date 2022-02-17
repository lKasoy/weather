package com.example.weather.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.weather.data.dao.DaoForecast
import com.example.weather.data.entity.Converters
import com.example.weather.data.entity.WeatherTable

@Database(entities = [WeatherTable::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract val getDaoForecast: DaoForecast
}