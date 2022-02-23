package ro.kofe.swapi.view.json

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import ro.kofe.swapi.model.Planet
import java.lang.IllegalArgumentException
import java.lang.reflect.Type

class PlanetDeserializer: JsonDeserializer<Planet> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Planet {
        if(json == null) throw IllegalArgumentException("Null json. How did we get here?")
        val jsonObj = json.asJsonObject
        return Planet(
            name = jsonObj.get("name").asString,
            population = jsonObj.get("population").asString,
            climate = jsonObj.get("climate").asString
        )
    }
}