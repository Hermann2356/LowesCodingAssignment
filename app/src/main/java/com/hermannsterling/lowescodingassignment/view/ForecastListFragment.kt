package com.hermannsterling.lowescodingassignment.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hermannsterling.lowescodingassignment.adapter.ForecastAdapter
import com.hermannsterling.lowescodingassignment.adapter.ForecastClickListener
import com.hermannsterling.lowescodingassignment.databinding.FragmentForecastListBinding
import com.hermannsterling.lowescodingassignment.model.ForecastItem
import com.hermannsterling.lowescodingassignment.utils.State
import com.hermannsterling.lowescodingassignment.viewmodel.MainViewModel

class ForecastListFragment : Fragment(), ForecastClickListener {
    private lateinit var binding: FragmentForecastListBinding
    private val viewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentForecastListBinding.inflate(layoutInflater, container, false)
        .also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val forecastLayoutManager = LinearLayoutManager(binding.rvWeatherForecast.context)
        val dividerItemDecoration = DividerItemDecoration(
            binding.rvWeatherForecast.context,
            forecastLayoutManager.orientation
        )

        viewModel.state.observe(viewLifecycleOwner) {
            val forecast = (it as State.Success).data
            with(binding) {
                forecastListToolBar.root.setNavigationOnClickListener {
                    findNavController().navigateUp()
                }
                forecastListToolBar.root.title = forecast.city.name
                rvWeatherForecast.apply {
                    layoutManager = forecastLayoutManager
                    adapter = ForecastAdapter(forecast.list, this@ForecastListFragment)
                    addItemDecoration(dividerItemDecoration)
                }
            }
        }
    }

    override fun onClick(forecast: ForecastItem) {
        val action = ForecastListFragmentDirections
            .actionForecastListFragment2ToForecastDescriptionFragment(forecast)
        findNavController().navigate(action)
    }
}