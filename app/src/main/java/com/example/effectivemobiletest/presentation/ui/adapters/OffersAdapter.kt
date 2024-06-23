package com.example.effectivemobiletest.presentation.ui.adapters

import android.annotation.SuppressLint
import com.example.effectivemobiletest.R
import com.example.effectivemobiletest.data.model.OffersDTO
import com.example.effectivemobiletest.databinding.SuggestionsItemsBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

@SuppressLint("SetTextI18n")
fun offersAdapter () = adapterDelegateViewBinding<OffersDTO.Offer, OffersDTO.Offer, SuggestionsItemsBinding>( viewBinding = { layoutInflater, parent ->
    SuggestionsItemsBinding.inflate(layoutInflater, parent, false)}) {


    bind {
        val str = "от"
        binding.bandImage.setImageDrawable( when (bindingAdapterPosition) {
            0 -> getDrawable(R.drawable.offers_1)
            1 -> getDrawable(R.drawable.offers_2)
            2 -> getDrawable(R.drawable.offers_3)
            else -> getDrawable(R.drawable.offers_1)
        })
        binding.bandName.text = item.title
        binding.cityName.text = item.town
        binding.price.text =  str + " " + item.price.value.toString()
    }
}