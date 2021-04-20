package com.ryanair.hosewski.data.repository

import com.ryanair.hosewski.data.api.API
import com.ryanair.hosewski.data.model.AirportsResponse
import com.ryanair.hosewski.data.model.FlightsResponse
import com.ryanair.hosewski.domain.common.Mapper
import com.ryanair.hosewski.domain.model.Airport
import com.ryanair.hosewski.domain.model.Flight
import com.ryanair.hosewski.domain.repository.RyanairRepository

class RyanairRepositoryImpl(private val api: API,
                            private val airportsMapper: Mapper<AirportsResponse, List<Airport>>,
                            private val flightsMapper: Mapper<FlightsResponse, List<Flight>>): RyanairRepository {

    override suspend fun getAirports(url: String): List<Airport> {
        return airportsMapper.mapFrom(api.getAirports(url))
    }

    override suspend fun getFlights(
        date: String,
        origin: String,
        destination: String,
        adults: String,
        teens: String,
        children: String
    ): List<Flight> {
        return flightsMapper.mapFrom(api.getFlights(date, origin, destination, adults, teens, children))
    }
}