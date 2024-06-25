package com.example.effectivemobiletest.data.repository.offers

import com.example.effectivemobiletest.data.model.OffersDTO

interface OffersRepository {
    suspend fun getOffers(): List<OffersDTO.Offer>
}