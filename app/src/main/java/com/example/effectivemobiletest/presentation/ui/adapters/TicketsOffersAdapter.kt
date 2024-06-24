package com.example.effectivemobiletest.presentation.ui.adapters

import android.content.res.ColorStateList
import com.example.effectivemobiletest.R
import com.example.effectivemobiletest.data.model.TicketsOffersDTO
import com.example.effectivemobiletest.databinding.TicketsOffersBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun ticketsOffersAdapter() = adapterDelegateViewBinding<TicketsOffersDTO.TicketsOffer, TicketsOffersDTO.TicketsOffer, TicketsOffersBinding>(
    viewBinding = { layoutInflater, parent ->
        TicketsOffersBinding.inflate(layoutInflater, parent, false)
    }
) {

    bind {
        binding.ticketsOffersName.text = item.title
        binding.ticketsOffersPrice.text = item.price.value.toString()
        binding.ticketsOffersTime.text = item.timeRange.reduce { acc, str -> "$acc $str" }
        binding.ticketsOffersImage.imageTintList =
            when (bindingAdapterPosition) {
                0 -> ColorStateList.valueOf(getColor(R.color.red))
                1 -> ColorStateList.valueOf(getColor(R.color.blue))
                else -> ColorStateList.valueOf(getColor(R.color.white))
            }
    }
}