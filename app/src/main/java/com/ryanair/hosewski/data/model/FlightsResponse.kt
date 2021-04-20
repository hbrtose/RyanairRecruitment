package com.ryanair.hosewski.data.model

data class FlightsResponse (
    val termsOfUse: String,
    val currency: String,
    val currPrecision: Long,
    val trips: List<Trip>,
    val serverTimeUTC: String
)

data class Trip (
    val origin: String,
    val originName: String,
    val destination: String,
    val destinationName: String,
    val dates: List<Date>
)

data class Date (
    val dateOut: String,
    val flights: List<Flight>
)

data class Flight (
    val faresLeft: Long,
    val flightKey: String,
    val infantsLeft: Int,
    val regularFare: RegularFare,
    val segments: List<Segment>,
    val flightNumber: String,
    val time: List<String>,
    val timeUTC: List<java.util.Date>,
    val duration: String
)

data class RegularFare (
    val fareKey: String,
    val fareClass: String,
    val fares: List<Fare>
)

data class Fare (
    val type: String,
    val amount: Double,
    val count: Long,
    val hasDiscount: Boolean,
    val publishedFare: Double,
    val discountInPercent: Int,
    val hasPromoDiscount: Boolean
)

data class Segment (
    val segmentNr: Long,
    val origin: String,
    val destination: String,
    val flightNumber: String,
    val time: List<String>,
    val timeUTC: List<String>,
    val duration: String
)
