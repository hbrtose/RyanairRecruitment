package com.ryanair.hosewski.presentation.scene.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(): ViewModel() {

    private val _origin = MutableLiveData<String>()
    private val _destination = MutableLiveData<String>()
    private val _infantsLeft = MutableLiveData<Int>()
    private val _fare = MutableLiveData<String>()
    private val _discount = MutableLiveData<Int>()
    val origin: LiveData<String> = _origin
    val destination: LiveData<String> = _destination
    val infantsLeft: LiveData<Int> = _infantsLeft
    val fare: LiveData<String> = _fare
    val discount: LiveData<Int> =_discount

    fun setData(args: DetailsFragmentArgs) {
        _origin.value = args.origin
        _destination.value = args.destination
        _infantsLeft.value = args.infants
        _fare.value = args.fare
        _discount.value = args.discount
    }
}