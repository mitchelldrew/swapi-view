package ro.kofe.swapi.view.provider

import retrofit2.Call
import retrofit2.http.GET
import ro.kofe.swapi.model.Page
import ro.kofe.swapi.model.Planet

interface PlanetService {
    @GET("planets")
    fun get(): Call<Page<Planet>>
}