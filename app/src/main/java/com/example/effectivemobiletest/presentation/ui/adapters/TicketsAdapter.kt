package com.example.effectivemobiletest.presentation.ui.adapters

import android.annotation.SuppressLint
import android.view.View
import com.example.effectivemobiletest.data.model.TicketsDTO
import com.example.effectivemobiletest.databinding.ItemTicketBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@SuppressLint("SetTextI18n")
fun ticketsAdapter() = adapterDelegateViewBinding<TicketsDTO.Ticket, TicketsDTO.Ticket, ItemTicketBinding>(
    viewBinding = { layoutInflater, parent ->
        ItemTicketBinding.inflate(layoutInflater, parent, false)
    }
) {

    bind {
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        val dateDeparture = LocalDateTime.parse(item.departure.date)
        val dateArrival = LocalDateTime.parse(item.arrival.date)
        val formattedDateDepartureTime = dateDeparture.format(formatter)
        val formattedDateArrivalTime = dateArrival.format(formatter)

        val formatterHours = DateTimeFormatter.ofPattern("HH")
        val formattedDateDepartureHours = dateDeparture.format(formatterHours)
        val formattedDateArrivalHours = dateArrival.format(formatterHours)
        val flightDurationCounter = formattedDateArrivalHours.toInt() - formattedDateDepartureHours.toInt()

        if (item.badge != null) {
            binding.badgeText.text = item.badge
        } else {
            binding.badge.visibility = View.GONE
        }
        binding.ticketPrice.text = item.price.value.toString() + "₽"
        binding.ticketsDepartureTime.text = formattedDateDepartureTime
        binding.ticketsArrivalTime.text = formattedDateArrivalTime
        binding.codeFrom.text = item.departure.airport
        binding.codeTo.text = item.arrival.airport
        binding.travelTime.text = kotlin.math.abs(flightDurationCounter).toString() + "ч в пути"

        if (item.hasTransfer) {
            binding.slash.visibility = View.GONE
            binding.noTransfers.visibility = View.GONE
        }
    }
}