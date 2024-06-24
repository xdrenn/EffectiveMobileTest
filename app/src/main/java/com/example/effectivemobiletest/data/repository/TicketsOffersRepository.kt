package com.example.effectivemobiletest.data.repository

import com.example.effectivemobiletest.data.model.TicketsOffersDTO

interface TicketsOffersRepository {
    suspend fun getTicketsOffers(): List<TicketsOffersDTO.TicketsOffer>
}