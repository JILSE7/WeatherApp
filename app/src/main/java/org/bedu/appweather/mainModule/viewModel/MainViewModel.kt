package org.bedu.appweather.mainModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.bedu.appweather.R
import org.bedu.appweather.entities.WeatherForecastEntity
import org.bedu.appweather.mainModule.model.MainRepository

class MainViewModel: ViewModel() {

    private val repository = MainRepository()


    private val result = MutableLiveData<WeatherForecastEntity>()

    private val snackbarMsg = MutableLiveData<Int>()

    private val isLoading = MutableLiveData<Boolean>()

    fun getResult(): LiveData<WeatherForecastEntity> = result
    fun getSnackbarMsg (): LiveData<Int> = snackbarMsg
    fun getIsLoaded (): LiveData<Boolean> = isLoading

    suspend fun getWeatherAndForecast(lat: Double, lon: Double, appId: String, units: String, lang: String) {
        viewModelScope.launch {
            try {
                isLoading.value = true
                val resultServer = repository.getWeatherAndForecast(lat, lon, appId, units, lang)
                result.value = resultServer
            }catch (e: Exception) {
                snackbarMsg.value = R.string.main_error_server
            }finally {
                isLoading.value = false
            }
        }

    }
}