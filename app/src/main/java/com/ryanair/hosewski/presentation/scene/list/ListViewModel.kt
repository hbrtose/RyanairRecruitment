package com.ryanair.hosewski.presentation.scene.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ryanair.hosewski.domain.model.Flight
import com.ryanair.hosewski.domain.usecase.GetFlightsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val getFlightsUseCase: GetFlightsUseCase): ViewModel() {

    private val _list = MutableLiveData<List<Flight>>()
    val list: LiveData<List<Flight>> = _list

    fun search(args: ListFragmentArgs) {
        viewModelScope.launch {
            getFlights(args)
        }
    }

    private suspend fun getFlights(args: ListFragmentArgs) {
        try {
            getFlightsUseCase.getFlights(
                args.date, args.origin, args.destination, args.adults, args.teens, args.children
            ).also {
                _list.value = it
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}