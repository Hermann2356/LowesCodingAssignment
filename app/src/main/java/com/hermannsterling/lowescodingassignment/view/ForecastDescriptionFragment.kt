package com.hermannsterling.lowescodingassignment.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.hermannsterling.lowescodingassignment.databinding.FragmentForecastDescriptionBinding
import com.hermannsterling.lowescodingassignment.utils.State
import com.hermannsterling.lowescodingassignment.viewmodel.MainViewModel

class ForecastDescriptionFragment : Fragment() {
    private lateinit var binding: FragmentForecastDescriptionBinding
    private val viewModel by activityViewModels<MainViewModel>()
    private val args by navArgs<ForecastDescriptionFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentForecastDescriptionBinding.inflate(layoutInflater, container, false)
        .also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val forecast = args.forecast
         val state = (viewModel.state.value as State.Success)

        binding.apply {
            toolBar.root.apply {
                setNavigationOnClickListener {
                    findNavController().navigateUp()
                }
                title = state.data.city.name
            }
            val temp = forecast.main.feels_like.toInt()
            tvTempDisplay.text = forecast.main.temp.toInt().toString()
            tvFeelsLikeDisplay.text = String.format("Feels like: %d", temp)
            tvMainDescription.text = forecast.weather[0].main
            tvSubDescription.text = forecast.weather[0].description
        }
    }
}