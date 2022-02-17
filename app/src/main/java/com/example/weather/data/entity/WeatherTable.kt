package com.example.weather.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

@Entity(tableName = "forecast_table")
data class WeatherTable(
    @PrimaryKey
    val dt: Long,
    var temp: Int,
    var humidity: Int,
    var cloudiness: Int,
    var windSpeed: Int,
    var date: Date?,
    var dayOfMonth: Int,
    var iconId: String
) {
    companion object {
        fun toDatabase(weatherForecast: WeatherForecast): List<WeatherTable> {
            return weatherForecast.list.map {
                WeatherTable(
                    dt = it.dt,
                    temp = it.main.temp.roundToInt(),
                    humidity = it.main.humidity,
                    cloudiness = it.clouds.all,
                    windSpeed = it.wind.speed.roundToInt(),
                    date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(it.dt_txt),
                    dayOfMonth = toDayOfMonth(it.dt_txt),
                    iconId = it.weather[0].icon
                )
            }
        }

        private fun toDayOfMonth(date: String): Int {
            val calendar = Calendar.getInstance()
            calendar.time = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date)
            return calendar.get(Calendar.DAY_OF_MONTH)
        }
    }
}