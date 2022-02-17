package com.example.weather.data.entity

import androidx.room.ColumnInfo
import java.util.*

data class WeekForecast(
    @ColumnInfo(name = "maxT")
    var maxT: Int,
    @ColumnInfo(name = "minT")
    var minT: Int,
    @ColumnInfo(name = "date")
    var date: Date,
    @ColumnInfo(name = "iconId")
    var iconId: String,
    @ColumnInfo(name = "c")
    var c: Int
)