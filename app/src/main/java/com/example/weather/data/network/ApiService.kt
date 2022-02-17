package com.example.weather.data.network

import com.example.weather.data.entity.WeatherForecast
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("?")
    suspend fun getForecastAsync(
        @Query("id") cityId: Int,
        @Query("lang") language: String,
        @Query("units") units: String,
        @Query("appid") appid: String,
    ): WeatherForecast
}