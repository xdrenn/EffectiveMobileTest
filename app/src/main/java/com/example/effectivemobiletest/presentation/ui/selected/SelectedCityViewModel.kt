package com.example.effectivemobiletest.presentation.ui.selected

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.effectivemobiletest.data.model.TicketsOffersDTO
import com.example.effectivemobiletest.domain.interactors.TicketsOffersInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class SelectedCityViewModel @Inject constructor(private val ticketsOffersInteractor: TicketsOffersInteractor) : ViewModel() {


    private val _cityFrom = MutableStateFlow("")
    val cityFrom = _cityFrom.asStateFlow()

    private val _cityTo = MutableStateFlow("")
    val cityTo = _cityTo.asStateFlow()

    private val _ticketsOffers= MutableStateFlow(listOf<TicketsOffersDTO.TicketsOffer>())
    val ticketsOffers = _ticketsOffers.asStateFlow()

    private val _flightDate = MutableStateFlow(System.currentTimeMillis())
    val flightDate = _flightDate.asStateFlow()

    init {
        getTicketsOffers()
    }


    fun setCityFrom(city: String) {
        _cityFrom.value = city
    }

    fun setCityTo(city: String) {
        _cityTo.value = city
    }

    fun setFlightDate(flightDate: Long) {
        _flightDate.value = flightDate
    }

    private fun getTicketsOffers() = viewModelScope.launch(Dispatchers.IO) {
        try {
            _ticketsOffers.value = ticketsOffersInteractor.getTicketsOffersList()
        } catch(e: IOException) {
            Log.d("Api error", e.toString())
        }
    }

}