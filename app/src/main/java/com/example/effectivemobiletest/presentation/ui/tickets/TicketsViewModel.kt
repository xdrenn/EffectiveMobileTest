package com.example.effectivemobiletest.presentation.ui.tickets

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.effectivemobiletest.data.model.TicketsDTO
import com.example.effectivemobiletest.domain.interactors.TicketsInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class TicketsViewModel @Inject constructor(private val ticketsInteractor: TicketsInteractor) : ViewModel()  {

    private val _flightRoute = MutableStateFlow("")
    val flightRoute = _flightRoute.asStateFlow()

    private val _flightDate = MutableStateFlow("")
    val flightDate = _flightDate.asStateFlow()

    private val _tickets= MutableStateFlow(listOf<TicketsDTO.Ticket>())
    val tickets = _tickets.asStateFlow()


    init {

        getTicketsList()
    }


    fun setFlightRoute(flightRoute: String) {
        _flightRoute.value = flightRoute
    }

    fun setFlightDate(flightDate: String) {
        _flightDate.value = flightDate
    }

    private fun getTicketsList()  = viewModelScope.launch(Dispatchers.IO) {
        try {
            _tickets.value = ticketsInteractor.getTicketsList()
        } catch(e: IOException) {
            Log.d("Api error", e.toString())
        }
    }
}
