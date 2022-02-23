package ro.kofe.swapi.view

import android.app.Application
import ro.kofe.swapi.view.di.ApplicationComponent
import ro.kofe.swapi.view.di.ApplicationModule
import ro.kofe.swapi.view.di.DaggerApplicationComponent

class SwapiApplication: Application() {

    companion object{
        @JvmStatic
        lateinit var graph: ApplicationComponent
    }


    override fun onCreate() {
        super.onCreate()
        graph = DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()
        graph.inject(this)
    }

}