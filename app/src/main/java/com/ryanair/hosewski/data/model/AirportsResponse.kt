package com.ryanair.hosewski.data.model

data class AirportsResponse (
    val stations: List<Station>
)

data class Station (
    val code: String,
    val name: String,
    val alternateName: String,
    val alias: List<String>,
    val countryCode: String,
    val countryName: String,
    val countryAlias: Any? = null,
    val countryGroupCode: String,
    val countryGroupName: String,
    val timeZoneCode: String,
    val latitude: String,
    val longitude: String,
    val mobileBoardingPass: Boolean,
    val markets: List<Market>,
    val notices: Any? = null
)

data class Market (
    val code: String,
    val group: Any? = null
)
