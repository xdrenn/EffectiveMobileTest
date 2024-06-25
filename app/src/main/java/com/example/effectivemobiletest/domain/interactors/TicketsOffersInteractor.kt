package com.example.effectivemobiletest.domain.interactors

import com.example.effectivemobiletest.data.model.TicketsOffersDTO
import com.example.effectivemobiletest.data.repository.ticketsoffers.TicketsOffersRepository
import javax.inject.Inject

class TicketsOffersInteractor @Inject constructor(private val ticketsOffersRepository: TicketsOffersRepository) {

    suspend fun getTicketsOffersList(): List<TicketsOffersDTO.TicketsOffer> {
        return ticketsOffersRepository.getTicketsOffers()
    }
}