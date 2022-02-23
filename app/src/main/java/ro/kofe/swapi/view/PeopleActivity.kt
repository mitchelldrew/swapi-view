package ro.kofe.swapi.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import ro.kofe.swapi.model.People
import ro.kofe.swapi.presenter.ipv.people.IPeopleInteractor
import ro.kofe.swapi.presenter.ipv.people.IPeopleView
import javax.inject.Inject

class PeopleActivity : AppCompatActivity(), IPeopleView {

    @Inject
    lateinit var interactor: IPeopleInteractor

    lateinit var output:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_people)
        SwapiApplication.graph.inject(this)
        interactor.setView(this)
        output = findViewById(R.id.people_output)
        findViewById<Button>(R.id.fetch_people).setOnClickListener { handleFetch() }
        findViewById<Button>(R.id.people_goto_films).setOnClickListener { handleFilms() }
        findViewById<Button>(R.id.people_goto_planet).setOnClickListener { handlePlanet() }
    }

    private fun handleFetch(){
        interactor.peopleButtonPressed()
    }

    private fun handleFilms(){
        startActivity(Intent(this, FilmActivity::class.java))
    }

    private fun handlePlanet(){
        startActivity(Intent(this, PlanetActivity::class.java))
    }

    override fun onResume() {
        super.onResume()
        interactor.viewResumed()
    }

    override fun onPause() {
        super.onPause()
        interactor.viewPaused()
    }

    override fun display(people: List<People>) {
        output.text = ""
        for(person in people){
            output.text = output.text.toString() + "${person.name}, ${person.eyeColor} \n"
        }
    }

    override fun error(e: Exception) {
        Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
    }
}