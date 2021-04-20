package com.ryanair.hosewski.domain.common

abstract class UseCase<P, T> {

    abstract suspend fun createObservable(data: P? = null): T

    suspend fun observable(withData: P? = null): T {
        return createObservable(withData)
    }
}