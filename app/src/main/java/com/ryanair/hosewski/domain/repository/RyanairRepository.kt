package com.ryanair.hosewski.domain.repository

import com.ryanair.hosewski.domain.model.Airport
import com.ryanair.hosewski.domain.model.Flight
import java.util.*

interface RyanairRepository {
    suspend fun getAirports(url: String): List<Airport>
    suspend fun getFlights(date: String, origin: String, destination: String, adults: String, teens: String, children: String): List<Flight>
}