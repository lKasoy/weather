package com.example.weather.data.services

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weather.constants.Constants
import com.example.weather.constants.Constants.THERMOMETER_UNITS
import com.example.weather.data.entity.WeekForecast
import com.example.weather.databinding.ForecastOfDayItemBinding
import com.example.weather.databinding.ForecastOfWeekItemBinding
import java.text.SimpleDateFormat

class ForecastOfWeekAdapter() :
ListAdapter<WeekForecast, ForecastOfWeekAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ForecastOfWeekItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result: WeekForecast = currentList[position]
        holder.bind(result)
    }

    class ViewHolder(private val binding: ForecastOfWeekItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(weekForecast: WeekForecast) {
            binding.apply {
                txtDate.text = SimpleDateFormat("EE").format(weekForecast.date)
                Glide.with(binding.root)
                    .load(Constants.BASE_IMG_URL + weekForecast.iconId + ".png")
                    .into(imgCurrentWeather)
                txtMaxT.text = weekForecast.maxT.toString() + THERMOMETER_UNITS + "/"
                txtMinT.text = weekForecast.minT.toString() + THERMOMETER_UNITS
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<WeekForecast>() {

        override fun areItemsTheSame(oldItem: WeekForecast, newItem: WeekForecast): Boolean {
            return oldItem.date == newItem.date
        }

        override fun areContentsTheSame(oldItem: WeekForecast, newItem: WeekForecast): Boolean {
            return oldItem.c == newItem.c
        }
    }
}