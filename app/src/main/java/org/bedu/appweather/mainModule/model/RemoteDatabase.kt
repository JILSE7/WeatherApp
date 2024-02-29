package org.bedu.appweather.mainModule.model

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.bedu.appweather.dao.WeatherService
import org.bedu.appweather.entities.WeatherForecastEntity
import org.bedu.appweather.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDatabase {
    private val retrofit = Retrofit.Builder().baseUrl(Constants.WEATHER_API_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()


    private val service = retrofit.create(WeatherService::class.java)


    suspend fun getWeatherForecastByCoordinates(lat: Double, lon: Double, appId: String, units: String, lang: String)
    : WeatherForecastEntity = withContext(Dispatchers.IO) {
        Log.d("test", "${lat} ${lon} ${appId} ${units} ${lang}")
        service.getWeatherForecastByCoordinates(lat, lon, appId, units, lang)
    }

}