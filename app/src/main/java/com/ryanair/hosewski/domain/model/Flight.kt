package com.ryanair.hosewski.domain.model

import java.util.*

data class Flight(
    val origin: String,
    val destination: String,
    val number: String,
    val date: Date,
    val price: Double,
    val fare: String,
    val discount: Int,
    val infantsLeft: Int,
    val duration: String,
    val currency: String
)