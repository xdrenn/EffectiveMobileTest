package com.example.effectivemobiletest.data.remote
import com.example.effectivemobiletest.data.model.OffersDTO
import com.example.effectivemobiletest.data.model.TicketsDTO
import com.example.effectivemobiletest.data.model.TicketsOffersDTO
import com.example.effectivemobiletest.utils.Constants
import retrofit2.http.GET

interface ApiService {

    @GET(Constants.OFFERS_API)
    suspend fun getOffersList (): OffersDTO

    @GET(Constants.TICKETS_OFFERS_API)
    suspend fun getTicketsOffersList (): TicketsOffersDTO

    @GET(Constants.TICKETS_API)
    suspend fun getTicketsList (): TicketsDTO
}