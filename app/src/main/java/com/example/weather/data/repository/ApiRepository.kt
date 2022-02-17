package com.example.weather.data.repository

import com.example.weather.constants.Constants.APPID
import com.example.weather.constants.Constants.LANGUAGE_RU
import com.example.weather.constants.Constants.UNITS_METRIC
import com.example.weather.constants.Constants.ZAPORIZHYA_ID
import com.example.weather.data.network.ApiService

class ApiRepository(private val apiService: ApiService) {

    suspend fun getForecastFromApi() = apiService.getForecastAsync(ZAPORIZHYA_ID, LANGUAGE_RU, UNITS_METRIC, APPID)
}