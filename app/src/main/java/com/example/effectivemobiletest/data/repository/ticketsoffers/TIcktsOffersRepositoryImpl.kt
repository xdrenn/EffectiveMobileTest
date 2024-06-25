package com.example.effectivemobiletest.data.repository.ticketsoffers

import com.example.effectivemobiletest.data.model.TicketsOffersDTO
import com.example.effectivemobiletest.data.remote.ApiService
import javax.inject.Inject

class TicketsOffersRepositoryImpl @Inject constructor (private val apiService: ApiService) :
    TicketsOffersRepository {
    override suspend fun getTicketsOffers(): List<TicketsOffersDTO.TicketsOffer> {
        return apiService.getTicketsOffersList().ticketsOffers
    }
}