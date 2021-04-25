package com.hermannsterling.lowescodingassignment.utils

import com.hermannsterling.lowescodingassignment.model.Forecast

sealed class State {
    data class Success(val data: Forecast) : State()
    data class Error(val msg: String) : State()
    object Loading : State()

    companion object {
        val State.isSuccess: Boolean
            get() = this is Success

        val State.isError: Boolean
            get() = this is Error

        val State.isLoading: Boolean
            get() = this is Loading

    }
}