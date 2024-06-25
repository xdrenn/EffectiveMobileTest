package com.example.effectivemobiletest.data.repository.offers

import com.example.effectivemobiletest.data.model.OffersDTO
import com.example.effectivemobiletest.data.remote.ApiService
import javax.inject.Inject

class OffersRepositoryImpl @Inject constructor (private val apiService: ApiService) :
    OffersRepository {
    override suspend fun getOffers(): List<OffersDTO.Offer> {
       return apiService.getOffersList().offers
    }
}