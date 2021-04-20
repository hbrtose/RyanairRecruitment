package com.ryanair.hosewski.domain.usecase

import com.ryanair.hosewski.domain.common.UseCase
import com.ryanair.hosewski.domain.model.Flight
import com.ryanair.hosewski.domain.repository.RyanairRepository
import java.lang.IllegalArgumentException

class GetFlightsUseCase(private val repository: RyanairRepository) : UseCase<GetFlightsUseCase.FlightsParams, List<Flight>>() {

    suspend fun getFlights(date: String, origin: String, dest: String, adults: String, teens: String, children: String): List<Flight> {
        return observable(FlightsParams(date, origin, dest, adults, teens, children))
    }

    override suspend fun createObservable(data: FlightsParams?): List<Flight> {
        data?.let {
            return repository.getFlights(it.date, it.origin, it.dest, it.adults, it.teens, it.children)
        } ?: throw IllegalArgumentException("No data provided")
    }

    data class FlightsParams(
        val date: String,
        val origin: String,
        val dest: String,
        val adults: String,
        val teens: String,
        val children: String
    )
}