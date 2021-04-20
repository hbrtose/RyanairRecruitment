package com.ryanair.hosewski.domain.usecase

import com.ryanair.hosewski.domain.common.UseCase
import com.ryanair.hosewski.domain.model.Airport
import com.ryanair.hosewski.domain.repository.RyanairRepository
import java.lang.IllegalArgumentException

class GetAirportsUseCase(private val repository: RyanairRepository) : UseCase<String, List<Airport>>() {

    suspend fun getAirports(url: String): List<Airport> {
        return observable(url)
    }

    override suspend fun createObservable(data: String?): List<Airport> {
        return data?.let {
            repository.getAirports(it)
        } ?: throw IllegalArgumentException("No url provided")
    }
}