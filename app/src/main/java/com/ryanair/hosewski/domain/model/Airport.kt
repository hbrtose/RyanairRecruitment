package com.ryanair.hosewski.domain.model

data class Airport(
    val code: String,
    val name: String,
) {
    override fun toString(): String {
        return "$code - $name"
    }
}