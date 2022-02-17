package com.example.weather.data.repository

import com.example.weather.data.dao.DaoForecast
import com.example.weather.data.entity.WeatherTable
import com.example.weather.data.entity.WeekForecast
import kotlinx.coroutines.flow.Flow
import java.util.*

class DaoRepository(private val daoForecast: DaoForecast) {

    suspend fun deleteAll() = daoForecast.deleteAll()

    suspend fun add(forecast: List<WeatherTable>) = daoForecast.add(forecast)

    fun getCurrentForecast() = daoForecast.getCurrentForecast()

    fun getDayForecast() = daoForecast.getDayForecast()

    suspend fun getWeekForecast(day: Int) =
        daoForecast.getWeekForecast(day)
}


