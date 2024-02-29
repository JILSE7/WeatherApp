package org.bedu.appweather.mainModule.model

import org.bedu.appweather.entities.WeatherForecastEntity

class MainRepository {
    private val remoteDatabase = RemoteDatabase()

    suspend fun getWeatherAndForecast(lat: Double, lon: Double, appId: String, units: String, lang: String): WeatherForecastEntity{
        return remoteDatabase.getWeatherForecastByCoordinates(lat, lon, appId, units, lang)
    }
}