package com.hermannsterling.lowescodingassignment.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.hermannsterling.lowescodingassignment.databinding.FragmentForecastSearchBinding
import com.hermannsterling.lowescodingassignment.utils.State
import com.hermannsterling.lowescodingassignment.utils.State.Companion.isError
import com.hermannsterling.lowescodingassignment.utils.State.Companion.isSuccess
import com.hermannsterling.lowescodingassignment.utils.safeNavigate
import com.hermannsterling.lowescodingassignment.viewmodel.MainViewModel

class ForecastSearchFragment : Fragment() {
    private lateinit var binding: FragmentForecastSearchBinding
    private val viewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentForecastSearchBinding.inflate(layoutInflater, container, false)
        .also { binding = it }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            btnLookup.setOnClickListener {
                viewModel.fetchWeatherForecast(etCityName.text.toString())
                initObserver()
            }
        }
    }

    private fun initObserver() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            binding.progressBar.visibility = View.GONE
            if (state.isSuccess) navigate()
            else if(state.isError) displayError((state as State.Error).msg)
            else binding.progressBar.visibility = View.VISIBLE
        }
    }

    private fun displayError(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }



    private fun navigate() {
        val action = ForecastSearchFragmentDirections
            .actionForecastSearchFragmentToForecastListFragment2()
        findNavController().safeNavigate(action)
    }
}