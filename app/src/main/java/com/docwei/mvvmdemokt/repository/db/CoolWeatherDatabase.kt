package com.docwei.mvvmdemokt.repository.db

object CoolWeatherDatabase {

    private var weatherDao: WeatherDao? = null
    fun getWeatherDao(): WeatherDao {
        if (weatherDao == null) weatherDao = WeatherDao()
        return weatherDao!!
    }

}