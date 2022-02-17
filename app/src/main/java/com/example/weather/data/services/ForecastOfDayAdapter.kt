package com.example.weather.data.services

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weather.constants.Constants.BASE_IMG_URL
import com.example.weather.constants.Constants.THERMOMETER_UNITS
import com.example.weather.data.entity.WeatherTable
import com.example.weather.databinding.ForecastOfDayItemBinding
import java.text.SimpleDateFormat

class ForecastOfDayAdapter() :
    ListAdapter<WeatherTable, ForecastOfDayAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ForecastOfDayItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result: WeatherTable = currentList[position]
        holder.bind(result)
    }

    class ViewHolder(private val binding: ForecastOfDayItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(weatherTable: WeatherTable) {
            binding.apply {
                    txtTemperature.text =
                        weatherTable.temp.toString() + THERMOMETER_UNITS
                    txtTime.text = SimpleDateFormat("HH:mm").format(weatherTable.date)
                    Glide.with(binding.root)
                        .load(BASE_IMG_URL + weatherTable.iconId + ".png")
                        .into(imgCurrentWeather)
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<WeatherTable>() {

        override fun areItemsTheSame(oldItem: WeatherTable, newItem: WeatherTable): Boolean {
            return oldItem.dt == newItem.dt
        }

        override fun areContentsTheSame(oldItem: WeatherTable, newItem: WeatherTable): Boolean {
            return oldItem == newItem
        }
    }
}