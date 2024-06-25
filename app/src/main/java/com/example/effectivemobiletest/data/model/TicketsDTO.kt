package com.example.effectivemobiletest.data.model

import com.google.gson.annotations.SerializedName

data class TicketsDTO(
    @SerializedName("tickets")
    val tickets: List<Ticket> = listOf()
) {
    data class Ticket(
        @SerializedName("id")
        val id: Int = 0,
        @SerializedName("badge")
        val badge: String? = null,
        @SerializedName("price")
        val price: Price = Price(),
        @SerializedName("provider_name")
        val providerName: String = "",
        @SerializedName("company")
        val company: String = "",
        @SerializedName("departure")
        val departure: Departure = Departure(),
        @SerializedName("arrival")
        val arrival: Arrival = Arrival(),
        @SerializedName("has_transfer")
        val hasTransfer: Boolean = false,
        @SerializedName("has_visa_transfer")
        val hasVisaTransfer: Boolean = false,
        @SerializedName("luggage")
        val luggage: Luggage = Luggage(),
        @SerializedName("hand_luggage")
        val handLuggage: HandLuggage = HandLuggage(),
        @SerializedName("is_returnable")
        val isReturnable: Boolean = false,
        @SerializedName("is_exchangable")
        val isExchangable: Boolean = false
    ) {
        data class Price(
            @SerializedName("value")
            val value: Int = 0
        )

        data class Departure(
            @SerializedName("town")
            val town: String = "",
            @SerializedName("date")
            val date: String = "",
            @SerializedName("airport")
            val airport: String = ""
        )

        data class Arrival(
            @SerializedName("town")
            val town: String = "",
            @SerializedName("date")
            val date: String = "",
            @SerializedName("airport")
            val airport: String = ""
        )

        data class Luggage(
            @SerializedName("has_luggage")
            val hasLuggage: Boolean = false,
            @SerializedName("price")
            val price: Price? = null
        ) {
            data class Price(
                @SerializedName("value")
                val value: Int = 0
            )
        }

        data class HandLuggage(
            @SerializedName("has_hand_luggage")
            val hasHandLuggage: Boolean = false,
            @SerializedName("size")
            val size: String? = null
        )
    }
}
