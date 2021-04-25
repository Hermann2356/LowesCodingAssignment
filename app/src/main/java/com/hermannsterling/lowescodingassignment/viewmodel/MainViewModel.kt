package com.hermannsterling.lowescodingassignment.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hermannsterling.lowescodingassignment.repo.ForecastRepository
import com.hermannsterling.lowescodingassignment.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val forecastRepo: ForecastRepository
) : ViewModel() {

    companion object {
        private const val TAG = "MainViewModel"
    }

    private val _state = MutableLiveData<State>()
    val state : LiveData<State> get() = _state

    fun fetchWeatherForecast(query: String) {
        _state.value = State.Loading

        viewModelScope.launch(Dispatchers.IO) {
            val responseState = forecastRepo.getWeatherForecast(query)
            _state.postValue(responseState)
            Log.d(TAG, responseState.toString())
        }
    }
}