package com.example.trackcovid_19

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.trackcovid_19.databinding.FragmentCovidTrackBinding
import com.example.trackcovid_19.model.CovidData
import com.example.trackcovid_19.viewmodel.CovidTrackViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi


class CovidTrackFragment : Fragment() {
    private lateinit var binding: FragmentCovidTrackBinding
    private lateinit var viewModel: CovidTrackViewModel
    private lateinit var nationalCovidList: List<CovidData>
    private lateinit var stateCovidList: List<CovidData>
    private lateinit var perStateDailyData: Map<String, List<CovidData>>

    @InternalCoroutinesApi
    @ExperimentalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCovidTrackBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(CovidTrackViewModel::class.java)


        observerLiveData()
        return binding.root
    }
    @InternalCoroutinesApi
    @ExperimentalCoroutinesApi
    private fun observerLiveData() {
        viewModel.mutableNationalCovidData.observe(viewLifecycleOwner, {
            it?.let {
                //for (i in it) { nationalCovidList.add(i) }
                nationalCovidList = it.reversed()

            }
        })

        viewModel.mutableStateCovidData.observe(viewLifecycleOwner, {
            it?.let { it0 ->
                //for (i in it) { stateCovidList.add(i) }
                stateCovidList = it0.reversed()
                perStateDailyData = it0.reversed().groupBy { it1 ->
                    it1.state
                }
            }
        })
    }


}