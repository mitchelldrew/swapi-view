package ro.kofe.swapi.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ro.kofe.swapi.model.Film
import ro.kofe.swapi.presenter.ipv.film.IFilmInteractor
import ro.kofe.swapi.presenter.ipv.film.IFilmView
import javax.inject.Inject

class FilmActivity: AppCompatActivity(), IFilmView {

    @Inject
    lateinit var interactor: IFilmInteractor

    lateinit var output: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film)
        SwapiApplication.graph.inject(this)
        interactor.setView(this)
        output = findViewById(R.id.film_output)
        findViewById<Button>(R.id.fetch_film).setOnClickListener { handleFetch() }
        findViewById<Button>(R.id.film_goto_people).setOnClickListener { handlePeople() }
        findViewById<Button>(R.id.film_goto_planet).setOnClickListener { handlePlanet() }
    }

    private fun handleFetch(){
        interactor.filmButtonPressed()
    }

    private fun handlePeople(){
        startActivity(Intent(this, PeopleActivity::class.java))
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

    override fun display(films: List<Film>) {
        output.text = ""
        for(film in films){
            output.text = output.text.toString() + "${film.title}, ${film.crawlText.subSequence(0,100)}..., ${film.releaseDate} \n"
        }
    }

    override fun error(e: Exception) {
        Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
    }
}