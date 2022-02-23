package ro.kofe.swapi.view.json

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import ro.kofe.swapi.model.People
import java.lang.IllegalArgumentException
import java.lang.reflect.Type

class PeopleDeserializer: JsonDeserializer<People> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): People {
        if(json == null) throw IllegalArgumentException("Null json. How did we get here?")
        val jsonObj = json.asJsonObject
        return People(
            name = jsonObj.get("name").asString,
            eyeColor = jsonObj.get("eye_color").asString
        )
    }
}