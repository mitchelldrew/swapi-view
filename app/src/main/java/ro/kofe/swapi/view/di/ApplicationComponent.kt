package ro.kofe.swapi.view.di

import dagger.Component
import ro.kofe.swapi.view.FilmActivity
import ro.kofe.swapi.view.PeopleActivity
import ro.kofe.swapi.view.PlanetActivity
import ro.kofe.swapi.view.SwapiApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(application: SwapiApplication)
    fun inject(activity: PeopleActivity)
    fun inject(activity: FilmActivity)
    fun inject(activity: PlanetActivity)
}
