package com.example.effectivemobiletest.presentation.ui.search

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class SearchViewModel : ViewModel() {

    private val _cityFrom = MutableStateFlow("")
    val cityFrom = _cityFrom.asStateFlow()

    private val _cityTo = MutableStateFlow("")
    val cityTo = _cityTo.asStateFlow()

    fun setCityFrom(city: String) {
        _cityFrom.value = city
    }

    fun setCityTo(city: String) {
        _cityTo.value = city
    }
}