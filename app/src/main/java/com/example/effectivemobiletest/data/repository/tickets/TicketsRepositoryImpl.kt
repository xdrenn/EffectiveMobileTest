package com.example.effectivemobiletest.data.repository.tickets

import com.example.effectivemobiletest.data.model.TicketsDTO
import com.example.effectivemobiletest.data.remote.ApiService
import javax.inject.Inject

class TicketsRepositoryImpl @Inject constructor (private val apiService: ApiService) :
    TicketsRepository {
    override suspend fun getTickets(): List<TicketsDTO.Ticket> {
        return apiService.getTicketsList().tickets
    }
}