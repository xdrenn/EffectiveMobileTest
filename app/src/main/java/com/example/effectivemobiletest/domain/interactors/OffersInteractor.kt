package com.example.effectivemobiletest.domain.interactors

import com.example.effectivemobiletest.data.model.OffersDTO
import com.example.effectivemobiletest.data.repository.offers.OffersRepository
import javax.inject.Inject

class OffersInteractor @Inject constructor(private val offersRepository: OffersRepository) {

    suspend fun getOffersList(): List<OffersDTO.Offer> {
        return offersRepository.getOffers()
    }
}