package com.example.effectivemobiletest.data.repository.tickets

import com.example.effectivemobiletest.data.model.TicketsDTO

interface TicketsRepository {

    suspend fun getTickets(): List<TicketsDTO.Ticket>
}