package com.example.weather.data.entity

data class WeatherForecast (
    val cod: String,
    val message: Long,
    val cnt: Long,
    val list: List<ListElement>,
    val city: City
)
data class City (
    val id: Long,
    val name: String,
    val coord: Coord,
    val country: String,
    val population: Long,
    val timezone: Long,
    val sunrise: Long,
    val sunset: Long
)

data class Coord (
    val lat: Double,
    val lon: Double
)

data class ListElement (
    val dt: Long,
    val main: MainClass,
    val weather: List<Weather>,
    val clouds: Clouds,
    val wind: Wind,
    val visibility: Long,
    val pop: Double,
    val sys: Sys,
    val dt_txt: String,
    val rain: Rain? = null
)

data class Clouds (
    val all: Int
)

data class MainClass (
    val temp: Double,
    val feelsLike: Double,
    val tempMin: Double,
    val tempMax: Double,
    val pressure: Long,
    val seaLevel: Long,
    val grndLevel: Long,
    val humidity: Int,
    val tempKf: Double
)

data class Rain (
    val the3H: Double
)

data class Sys (
    val pod: String
)

data class Weather (
    val id: Long,
    val main: String,
    val description: String,
    val icon: String
)

data class Wind (
    val speed: Double,
    val deg: Long,
    val gust: Double
)