package ro.kofe.swapi.view.json

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import ro.kofe.swapi.model.Film
import java.lang.IllegalArgumentException
import java.lang.reflect.Type

class FilmDeserializer: JsonDeserializer<Film> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Film {
        if(json == null) throw IllegalArgumentException("Null json. How did we get here?")
        val jsonObj = json.asJsonObject
        return Film(
            title = jsonObj.get("title").asString,
            crawlText = jsonObj.get("opening_crawl").asString,
            releaseDate = jsonObj.get("release_date").asString
        )
    }
}