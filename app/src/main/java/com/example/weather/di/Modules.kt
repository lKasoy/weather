package com.example.weather.di

import androidx.room.Room
import com.example.weather.constants.Constants.BASE_URL
import com.example.weather.data.AppDatabase
import com.example.weather.data.network.ApiService
import com.example.weather.data.repository.ApiRepository
import com.example.weather.data.repository.DaoRepository
import com.example.weather.data.repository.DecoratorRepository
import com.example.weather.ui.viewModels.ForecastViewModel
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {

    viewModel {
        ForecastViewModel(get())
    }
}

val netModule = module {
    factory { OkHttpClient.Builder().build() }
    factory { GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create() }
    factory {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .client(get())
            .build()
            .create(ApiService::class.java)
    }
}

val dataBaseModule = module {
    single { Room.databaseBuilder(androidContext(), AppDatabase::class.java, "forecast").build() }
    single { get<AppDatabase>().getDaoForecast }
}

val repositoryModule = module {
    single { ApiRepository(get()) }
    single { DaoRepository(get()) }
    single { DecoratorRepository(get(), get()) }
}