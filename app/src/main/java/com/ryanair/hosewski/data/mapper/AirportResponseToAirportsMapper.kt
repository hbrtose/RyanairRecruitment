package com.ryanair.hosewski.data.mapper

import com.ryanair.hosewski.data.model.AirportsResponse
import com.ryanair.hosewski.domain.common.Mapper
import com.ryanair.hosewski.domain.model.Airport

class AirportResponseToAirportsMapper: Mapper<AirportsResponse, List<Airport>>() {
    override fun mapFrom(from: AirportsResponse): List<Airport> {
        return from.stations.map { Airport(it.code, it.name) }
    }
}