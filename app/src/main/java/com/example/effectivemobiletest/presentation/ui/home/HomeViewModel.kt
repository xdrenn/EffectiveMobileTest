package com.example.effectivemobiletest.presentation.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.effectivemobiletest.data.model.OffersDTO
import com.example.effectivemobiletest.domain.interactors.OffersInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val offersInteractor: OffersInteractor) : ViewModel() {

    private val _offers= MutableStateFlow(listOf<OffersDTO.Offer>())
    val offers = _offers.asStateFlow()

    private val _cityFrom = MutableStateFlow("")
    val cityFrom = _cityFrom.asStateFlow()

    init {
        getOffers()
    }

    private fun getOffers() = viewModelScope.launch(Dispatchers.IO) {
         try {
             _offers.value = offersInteractor.getOffersList()
         } catch(e: IOException) {
             Log.d("Api error", e.toString())
         }
    }

    fun setCityFrom(city: String) {
        _cityFrom.value = city
    }
}