package ro.kofe.swapi.view.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ro.kofe.swapi.model.Film
import ro.kofe.swapi.model.People
import ro.kofe.swapi.model.Planet
import ro.kofe.swapi.presenter.Freezer
import ro.kofe.swapi.presenter.IFreezer
import ro.kofe.swapi.presenter.ipv.film.FilmInteractor
import ro.kofe.swapi.presenter.ipv.film.FilmPresenter
import ro.kofe.swapi.presenter.ipv.film.IFilmInteractor
import ro.kofe.swapi.presenter.ipv.film.IFilmPresenter
import ro.kofe.swapi.presenter.ipv.people.IPeopleInteractor
import ro.kofe.swapi.presenter.ipv.people.IPeoplePresenter
import ro.kofe.swapi.presenter.ipv.people.PeopleInteractor
import ro.kofe.swapi.presenter.ipv.people.PeoplePresenter
import ro.kofe.swapi.presenter.ipv.planet.IPlanetInteractor
import ro.kofe.swapi.presenter.ipv.planet.IPlanetPresenter
import ro.kofe.swapi.presenter.ipv.planet.PlanetInteractor
import ro.kofe.swapi.presenter.ipv.planet.PlanetPresenter
import ro.kofe.swapi.presenter.provider.ILoggingProvider
import ro.kofe.swapi.presenter.provider.IProvider
import ro.kofe.swapi.view.SwapiApplication
import ro.kofe.swapi.view.json.FilmDeserializer
import ro.kofe.swapi.view.json.PeopleDeserializer
import ro.kofe.swapi.view.json.PlanetDeserializer
import ro.kofe.swapi.view.provider.*

@Module
class ApplicationModule(private val application: SwapiApplication) {

    @Provides
    fun provideRetrofit(gson: Gson, okHttp: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://swapi.dev/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttp)
            .build()
    }

    @Provides
    fun provideOKHttpClient(): OkHttpClient {
        return OkHttpClient()
    }

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(People::class.java, PeopleDeserializer())
            .registerTypeAdapter(Film::class.java, FilmDeserializer())
            .registerTypeAdapter(Planet::class.java, PlanetDeserializer())
            .create()
    }

    @Provides
    fun provideIPeopleInteractor(presenter: IPeoplePresenter,loggingProvider: ILoggingProvider): IPeopleInteractor {
        return PeopleInteractor(
            presenter,
            loggingProvider
        )
    }

    @Provides
    fun provideILoggingProvider(): ILoggingProvider {
        return LoggingProvider()
    }

    @Provides
    fun provideIPeoplePresenter(freezer: IFreezer,peopleProvider: IProvider<People>, loggingProvider: ILoggingProvider): IPeoplePresenter {
        return PeoplePresenter(
            freezer,
            peopleProvider,
            loggingProvider
        )
    }

    @Provides
    fun provideIFreezer() : IFreezer {
        return Freezer()
    }

    @Provides
    fun provideIProviderPeople(service: PeopleService) : IProvider<People> {
        return PeopleProvider(service)
    }

    @Provides
    fun providePeopleService(retrofit: Retrofit): PeopleService {
        return retrofit.create(PeopleService::class.java)
    }

    @Provides
    fun provideIFilmInteractor(presenter: IFilmPresenter, loggingProvider: ILoggingProvider): IFilmInteractor {
        return FilmInteractor(presenter, loggingProvider)
    }

    @Provides
    fun provideIFilmPresenter(freezer: IFreezer, loggingProvider: ILoggingProvider, filmProvider: IProvider<Film>): IFilmPresenter {
        return FilmPresenter(freezer,filmProvider, loggingProvider)
    }

    @Provides
    fun provideIProviderFilm(service: FilmService): IProvider<Film> {
        return FilmProvider(service)
    }

    @Provides
    fun provideFilmService(retrofit: Retrofit): FilmService {
        return retrofit.create(FilmService::class.java)
    }

    @Provides
    fun provideIPlanetInteractor(presenter: IPlanetPresenter, loggingProvider: ILoggingProvider): IPlanetInteractor {
        return PlanetInteractor(presenter, loggingProvider)
    }

    @Provides
    fun provideIPlanetPresenter(freezer: IFreezer, provider: IProvider<Planet>, loggingProvider: ILoggingProvider): IPlanetPresenter {
        return PlanetPresenter(freezer,provider,loggingProvider)
    }

    @Provides
    fun provideIProviderPlanet(service:PlanetService): IProvider<Planet> {
        return PlanetProvider(service)
    }

    @Provides
    fun providePlanetService(retrofit: Retrofit): PlanetService {
        return retrofit.create(PlanetService::class.java)
    }
}