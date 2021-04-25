package com.hermannsterling.lowescodingassignment.tests

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.hermannsterling.lowescodingassignment.resources.FakeForecastRepo
import com.hermannsterling.lowescodingassignment.resources.utils.getOrAwaitValueTest
import com.hermannsterling.lowescodingassignment.utils.State.Companion.isError
import com.hermannsterling.lowescodingassignment.utils.State.Companion.isLoading
import com.hermannsterling.lowescodingassignment.utils.State.Companion.isSuccess
import com.hermannsterling.lowescodingassignment.viewmodel.MainViewModel
import junit.framework.Assert
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock

@ExperimentalCoroutinesApi
class MainViewModelTest {

    private lateinit var testCoroutineDispatcher: TestCoroutineDispatcher
    private lateinit var viewModel: MainViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private val forecastRepo = FakeForecastRepo()

    @Before
    fun setUp() {
        testCoroutineDispatcher = TestCoroutineDispatcher()
        viewModel = MainViewModel(forecastRepo)
    }

    @After
    fun tearDown() {
        testCoroutineDispatcher.cancel()
    }

    @Test
    fun `Test success state when city is found`() {
        testCoroutineDispatcher.runBlockingTest {
            val job = viewModel.fetchWeatherForecast("New York")
            var state = viewModel.state.getOrAwaitValueTest()
            while(state.isLoading)
                state = viewModel.state.getOrAwaitValueTest()
            assertTrue(state.isSuccess)
        }
    }

    @Test
    fun `Test error state when user input is blank`() {
        testCoroutineDispatcher.runBlockingTest {
            val job = viewModel.fetchWeatherForecast("")
            var state = viewModel.state.getOrAwaitValueTest()
            while(state.isLoading)
                state = viewModel.state.getOrAwaitValueTest()
            assertTrue(state.isError)
        }
    }

    @Test
    fun `Test error state when city is not found`() {
        testCoroutineDispatcher.runBlockingTest {
            val job = viewModel.fetchWeatherForecast("werw")
            var state = viewModel.state.getOrAwaitValueTest()
            while(state.isLoading)
                state = viewModel.state.getOrAwaitValueTest()
            assertTrue(state.isError)
        }
    }
}