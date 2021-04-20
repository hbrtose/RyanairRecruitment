package com.ryanair.hosewski.di

import com.ryanair.hosewski.data.api.API
import com.ryanair.hosewski.data.mapper.AirportResponseToAirportsMapper
import com.ryanair.hosewski.data.mapper.FlightResponseToFlightsMapper
import com.ryanair.hosewski.data.repository.RyanairRepositoryImpl
import com.ryanair.hosewski.domain.repository.RyanairRepository
import com.ryanair.hosewski.domain.usecase.GetAirportsUseCase
import com.ryanair.hosewski.domain.usecase.GetFlightsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    //yeah i know there should be generic arguments on mappers but hilt keeps insisting on using java.util.List instead of kotlin stdlib one
    @Provides
    fun provideAirportsMapper(): AirportResponseToAirportsMapper {
        return AirportResponseToAirportsMapper()
    }

    @Provides
    fun provideFlightsMapper(): FlightResponseToFlightsMapper {
        return FlightResponseToFlightsMapper()
    }

    @Provides
    fun provideRepository(api: API, airportsMapper: AirportResponseToAirportsMapper, flightsMapper: FlightResponseToFlightsMapper): RyanairRepository {
        return RyanairRepositoryImpl(api, airportsMapper, flightsMapper)
    }

    @Provides
    fun provideGetAirportsUseCase(repository: RyanairRepository): GetAirportsUseCase {
        return GetAirportsUseCase(repository)
    }

    @Provides
    fun provideGetFlightsUseCase(repository: RyanairRepository): GetFlightsUseCase {
        return GetFlightsUseCase(repository)
    }
}