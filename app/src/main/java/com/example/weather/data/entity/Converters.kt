package com.example.weather.data.entity

import androidx.room.TypeConverter
import java.util.*


class Converters {
    @TypeConverter
    fun fromDate(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToDate(date: Date?): Long? {
        return date?.time?.toLong()
    }
}
