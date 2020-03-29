package com.docwei.mvvmdemokt.repository

import com.docwei.mvvmdemokt.repository.db.WeatherDao
import com.docwei.mvvmdemokt.model.Weather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepository private constructor(
    private val weatherDao: WeatherDao,
    private val network: CoolWeatherNetwork
) {

    suspend fun getWeather(weatherId: String): Weather {
        var weather = weatherDao.getCachedWeatherInfo()
        if (weather == null) {
            weather = requestWeather(weatherId)
        }
        return weather
    }

    suspend fun refreshWeather(weatherId: String) = requestWeather(weatherId)

    suspend fun getBingPic(): String {
        var url = weatherDao.getCachedBingPic()
        if (url == null) url = requestBingPic()
        return url
    }

    suspend fun refreshBingPic() = requestBingPic()


    private suspend fun requestWeather(weatherId: String) = withContext(Dispatchers.IO) {
        val heWeather = network.fetchWeather(weatherId)
        val weather = heWeather.weather!![0]
        weatherDao.cacheWeatherInfo(weather)
        weather
    }

    private suspend fun requestBingPic() = withContext(Dispatchers.IO) {
        val url = network.fetchBingPic()
        weatherDao.cacheBingPic(url)
        url
    }

    //单例对象
    companion object {

        private lateinit var instance: WeatherRepository

        fun getInstance(weatherDao: WeatherDao, network: CoolWeatherNetwork): WeatherRepository {
            if (!Companion::instance.isInitialized) {
                synchronized(WeatherRepository::class.java) {
                    if (!Companion::instance.isInitialized) {
                        instance =
                            WeatherRepository(
                                weatherDao,
                                network
                            )
                    }
                }
            }
            return instance
        }

    }

}