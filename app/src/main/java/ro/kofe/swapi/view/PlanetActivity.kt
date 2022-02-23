package ro.kofe.swapi.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import ro.kofe.swapi.model.People
import ro.kofe.swapi.model.Planet
import ro.kofe.swapi.presenter.ipv.planet.IPlanetInteractor
import ro.kofe.swapi.presenter.ipv.planet.IPlanetView
import javax.inject.Inject

class PlanetActivity : AppCompatActivity(), IPlanetView {

    @Inject
    lateinit var interactor: IPlanetInteractor

    lateinit var output:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planet)
        SwapiApplication.graph.inject(this)
        interactor.setView(this)
        output = findViewById(R.id.planet_output)
        findViewById<Button>(R.id.fetch_planet).setOnClickListener { handleFetch() }
        findViewById<Button>(R.id.planet_goto_films).setOnClickListener { handleFilms() }
        findViewById<Button>(R.id.planet_goto_people).setOnClickListener { handlePeople() }
    }

    private fun handleFetch(){
        interactor.planetButtonPressed()
    }

    private fun handleFilms(){
        startActivity(Intent(this, FilmActivity::class.java))
    }

    private fun handlePeople(){
        startActivity(Intent(this, PeopleActivity::class.java))
    }

    override fun onResume() {
        super.onResume()
        interactor.viewResumed()
    }

    override fun onPause() {
        super.onPause()
        interactor.viewPaused()
    }

    override fun display(planets: List<Planet>) {
        output.text = ""
        for(planet in planets){
            output.text = output.text.toString() + "${planet.name}, ${planet.climate}, ${planet.population} \n"
        }
    }

    override fun error(e: Exception) {
        Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
    }
}