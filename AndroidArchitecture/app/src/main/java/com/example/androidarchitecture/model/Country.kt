package com.example.androidarchitecture.model

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.google.gson.annotations.SerializedName
import java.lang.reflect.Type


data class Country(
    @SerializedName(value = "common")
    val countryName:String
)


internal class MyDeserializer : JsonDeserializer<Country?> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        je: JsonElement,
        type: Type?,
        jdc: JsonDeserializationContext?
    ): Country {
        // Get the "content" element from the parsed JSON
        val content = je.asJsonObject["name"]

        // Deserialize it. You use a new instance of Gson to avoid infinite recursion
        // to this deserializer
        return Gson().fromJson(content, Country::class.java)
    }
}
