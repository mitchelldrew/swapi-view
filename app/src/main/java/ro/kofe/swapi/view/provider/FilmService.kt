package ro.kofe.swapi.view.provider

import retrofit2.Call
import retrofit2.http.GET
import ro.kofe.swapi.model.Page
import ro.kofe.swapi.model.Film

interface FilmService {
    @GET("films")
    fun get(): Call<Page<Film>>
}