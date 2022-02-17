package com.example.weather.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.weather.constants.Constants.BASE_IMG_URL
import com.example.weather.constants.Constants.HUMIDITY_UNICODE
import com.example.weather.constants.Constants.HUMIDITY_UNITS
import com.example.weather.constants.Constants.THERMOMETER_UNICODE
import com.example.weather.constants.Constants.THERMOMETER_UNITS
import com.example.weather.constants.Constants.WIND_UNICODE
import com.example.weather.constants.Constants.WIND_UNITS
import com.example.weather.data.services.ForecastOfDayAdapter
import com.example.weather.data.services.ForecastOfWeekAdapter
import com.example.weather.databinding.FragmentWeatherBinding
import com.example.weather.ui.viewModels.ForecastViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat

class ForecastFragment : Fragment() {

    private lateinit var binding: FragmentWeatherBinding
    private val forecastOfDayAdapter = ForecastOfDayAdapter()
    private val forecastOfWeekAdapter = ForecastOfWeekAdapter()
    private val forecastViewModel by viewModel<ForecastViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rclOfDay.adapter = forecastOfDayAdapter
        binding.rclOfWeek.adapter = forecastOfWeekAdapter
        binding.rclOfDay.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        forecastViewModel.cityName.observe(viewLifecycleOwner, {
            binding.txtCityName.text = it
        })

        forecastViewModel.currentForecast.observe(viewLifecycleOwner, {
            binding.apply {
                loadImageCurrentWeather(BASE_IMG_URL + it.iconId + "@2x.png")
                txtCurrentDate.text =
                    SimpleDateFormat("EE, dd MMMM").format(it.date)
                txtTemperature.text =
                    THERMOMETER_UNICODE + it.temp.toString() + THERMOMETER_UNITS
                txtHumidity.text =
                    HUMIDITY_UNICODE + it.humidity.toString() + HUMIDITY_UNITS
                txtWindSpeed.text =
                    WIND_UNICODE + it.windSpeed.toString() + WIND_UNITS
            }
        })

        forecastViewModel.dayForecast.observe(viewLifecycleOwner, {
            forecastOfDayAdapter.submitList(it)
        })

        forecastViewModel.weekForecast.observe(viewLifecycleOwner, {
            forecastOfWeekAdapter.submitList(it)
        })

    }

    private fun loadImageCurrentWeather(url: String) {
        Glide.with(this)
            .load(url)
            .into(binding.imgCurrentWeather)
    }
}