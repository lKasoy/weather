package com.example.weather.data.repository

import android.util.Log
import com.example.weather.data.entity.WeatherTable
import com.example.weather.data.entity.WeatherTable.Companion.toDatabase
import com.example.weather.data.entity.WeekForecast
import kotlinx.coroutines.flow.Flow

class DecoratorRepository(
    private val apiRepository: ApiRepository,
    private val daoRepository: DaoRepository
) {
    private var isFirstResponse: Boolean = true

    val currentForecast: Flow<WeatherTable> = daoRepository.getCurrentForecast()
    val dayForecast: Flow<List<WeatherTable>> = daoRepository.getDayForecast()

    suspend fun getForecast(): String {
        return try {
            val forecastWeather = apiRepository.getForecastFromApi()
            val forecast = toDatabase(forecastWeather)
            if (isFirstResponse) {
                daoRepository.deleteAll()
                isFirstResponse = false
            }
            daoRepository.add(forecast)
            forecastWeather.city.name
        } catch (e: Exception) {
            Log.d("test", e.toString())
            "$e"
        }
    }

    suspend fun getWeekForecast(day: Int): WeekForecast {
        return daoRepository.getWeekForecast(day)
    }
}