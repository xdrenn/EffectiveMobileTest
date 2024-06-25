package com.example.effectivemobiletest.domain.interactors

import com.example.effectivemobiletest.data.model.TicketsDTO
import com.example.effectivemobiletest.data.repository.tickets.TicketsRepository
import javax.inject.Inject

class TicketsInteractor @Inject constructor(private val ticketsRepository: TicketsRepository) {

    suspend fun getTicketsList(): List<TicketsDTO.Ticket> {
        return ticketsRepository.getTickets()
    }
}