package com.example.trackcovid_19.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trackcovid_19.model.CovidData
import com.example.trackcovid_19.service.CovidAPIService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import java.lang.Exception

class CovidTrackViewModel : ViewModel() {
/*
private var _mutableNationalCovidData = MutableLiveData<List<CovidData>>()
val mutableNationalCovidData: MutableLiveData<List<CovidData>>
    get() = _mutableNationalCovidData

@ExperimentalCoroutinesApi
fun getNationalCovidDataFromAPI() {
    viewModelScope.launch(Dispatchers.IO) {
        try {
            _mutableNationalCovidData.value = CovidAPIService.retrofitInstance.getNationalData()
        }catch (e :Exception) {
            Log.d("error", "${e.message}")
        }
    }
}

private var _mutableStateCovidData = MutableLiveData<List<CovidData>>()
val mutableStateCovidData: MutableLiveData<List<CovidData>>
    get() = _mutableStateCovidData


@ExperimentalCoroutinesApi
fun getStateCovidDataFromAPI() {
    viewModelScope.launch(Dispatchers.IO) {
        try {
            _mutableStateCovidData.postValue() = CovidAPIService.retrofitInstance.getStatesData()
        }catch (e :Exception) {
            Log.d("error", "${e.message}")
        }
    }
}


*/
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
val mutableStateCovidData = MutableLiveData<List<CovidData>>().apply {
    viewModelScope.launch(Dispatchers.IO) {
        val result =  CovidAPIService.retrofitInstance.getStatesData()
        postValue(result)
    }
}

    @InternalCoroutinesApi
    @ExperimentalCoroutinesApi
    val mutableNationalCovidData = MutableLiveData<List<CovidData>>().apply {
        viewModelScope.launch(Dispatchers.IO) {
            val result =  CovidAPIService.retrofitInstance.getNationalData()
            postValue(result)
        }
    }

}