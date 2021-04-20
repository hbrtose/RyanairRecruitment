package com.ryanair.hosewski.data.mapper

import com.ryanair.hosewski.data.model.FlightsResponse
import com.ryanair.hosewski.domain.common.Mapper
import com.ryanair.hosewski.domain.model.Flight
import java.util.*

class FlightResponseToFlightsMapper: Mapper<FlightsResponse, List<Flight>>() {
    override fun mapFrom(from: FlightsResponse): List<Flight> {
        return from.trips.flatMap { trip ->
            trip.dates.flatMap { date ->
                date.flights.filter { it.regularFare.fares.isNotEmpty() }.map {
                    Flight(trip.origin, trip.destination, it.flightNumber, it.timeUTC[0], it.regularFare.fares[0].amount, it.regularFare.fareClass, it.regularFare.fares[0].discountInPercent, it.infantsLeft, it.duration, from.currency)
                }
            }
        }
    }
}