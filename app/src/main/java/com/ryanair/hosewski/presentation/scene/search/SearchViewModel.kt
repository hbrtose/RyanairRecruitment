package com.ryanair.hosewski.presentation.scene.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ryanair.hosewski.data.URL
import com.ryanair.hosewski.domain.model.Airport
import com.ryanair.hosewski.domain.usecase.GetAirportsUseCase
import com.ryanair.hosewski.presentation.scene.list.ListFragmentArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val getAirportsUseCase: GetAirportsUseCase) :
    ViewModel() {

    private val _airports = MutableLiveData<List<Airport>>()
    val airports: LiveData<List<Airport>> = _airports
    val origin = MutableLiveData<String>()
    val destination = MutableLiveData<String>()
    val date = MutableLiveData<String>()
    val adults = MutableLiveData<String>()
    val teens = MutableLiveData<String>()
    val children = MutableLiveData<String>()
    private val _navigateToList = MutableLiveData<ListFragmentArgs?>(null)
    val navigateToList: LiveData<ListFragmentArgs?> = _navigateToList

    init {
        viewModelScope.launch {
            getAirports()
        }
    }

    private suspend fun getAirports() {
        try {
            getAirportsUseCase.getAirports(URL.AIRPORTS).also {
                _airports.value = it
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun setDate(dateString: String) {
        date.value = dateString
    }

    fun search() {
        if (!origin.value.isNullOrEmpty() && !destination.value.isNullOrEmpty() && !date.value.isNullOrEmpty()) {
            _navigateToList.value = ListFragmentArgs(
                origin.value.toString().substring(0..2),
                destination.value.toString().substring(0..2),
                date.value.toString(),
                adults.value?.toString() ?: "0",
                teens.value?.toString() ?: "0",
                children.value?.toString() ?: "0"
            )
        }
    }

    fun resetNavigation() {
        _navigateToList.value = null
    }
}