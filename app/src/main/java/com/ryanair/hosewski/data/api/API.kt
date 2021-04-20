package com.ryanair.hosewski.data.api

import com.ryanair.hosewski.data.model.AirportsResponse
import com.ryanair.hosewski.data.model.FlightsResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface API {
    @GET
    suspend fun getAirports(@Url url: String): AirportsResponse

    @GET("Availability")
    suspend fun getFlights(
        @Query("dateout") dateOut: String,
        @Query("origin") origin: String,
        @Query("destination") destination: String,
        @Query("adt") adults: String,
        @Query("teen") teens: String,
        @Query("chd") children: String,
        @Query("Disc") disc: Int = 0,
        @Query("ToUs") toUs: String = "AGREED",
        @Query("inf") inf: Int = 0,
        @Query("flexDaysOut") flexDaysOut: Int = 3,
        @Query("flexDaysBeforeOut") flexDaysBeforeOut: Int = 3,
        @Query("roundtrip") roundtrip: Boolean = false,
    ): FlightsResponse
}