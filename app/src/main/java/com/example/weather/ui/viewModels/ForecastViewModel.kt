package com.example.weather.ui.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.data.entity.WeatherTable
import com.example.weather.data.entity.WeekForecast
import com.example.weather.data.repository.DecoratorRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*

class ForecastViewModel(
    private val decoratorRepository: DecoratorRepository
) : ViewModel() {

    private val _cityName = MutableLiveData<String>()
    val cityName: LiveData<String> = _cityName

    private val _currentForecast = MutableLiveData<WeatherTable>()
    val currentForecast: LiveData<WeatherTable> = _currentForecast

    private val _dayForecast = MutableLiveData<List<WeatherTable>>()
    val dayForecast: LiveData<List<WeatherTable>> = _dayForecast

    private val _weekForecast = MutableLiveData<List<WeekForecast>>()
    val weekForecast: LiveData<List<WeekForecast>> = _weekForecast

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            try {
                _cityName.value = decoratorRepository.getForecast()
                subscribe()
            } catch (e: Exception) {
                Log.d("test", e.toString())
                e.printStackTrace()
            }
        }
    }

    private fun subscribe() {
        viewModelScope.launch {
            decoratorRepository.currentForecast.collect {
                _currentForecast.value = it
            }
        }
        viewModelScope.launch {
            decoratorRepository.dayForecast.collect {
                _dayForecast.value = it
            }
        }

        viewModelScope.launch {
            for (i in 0..5) {
                val forecast = decoratorRepository.getWeekForecast(
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + i
                )
                val currentForecast = _weekForecast.value ?: listOf()
                _weekForecast.value = currentForecast + forecast
            }
        }
    }
}


