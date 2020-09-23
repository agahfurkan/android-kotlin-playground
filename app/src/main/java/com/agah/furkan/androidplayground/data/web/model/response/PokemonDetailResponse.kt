package com.agah.furkan.androidplayground.data.web.model.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonDetailResponse(
    @Json(name = "abilities")
    val abilities: List<Ability>,
    @Json(name = "id")
    val id: Int,
    @Json(name = "location_area_encounters")
    val locationAreaEncounters: String,
    @Json(name = "stats")
    val stats: List<Stats>
) {
    @JsonClass(generateAdapter = true)
    data class Ability(
        @Json(name = "ability")
        val ability: Ability2,
        @Json(name = "is_hidden")
        val isHidden: Boolean,
        @Json(name = "slot")
        val slot: Int
    ) {
        @JsonClass(generateAdapter = true)
        data class Ability2(
            @Json(name = "name")
            val name: String,
            @Json(name = "url")
            val url: String
        )
    }

    @JsonClass(generateAdapter = true)
    data class Stats(
        @Json(name = "base_stat")
        val baseStat: Int,
        @Json(name = "effort")
        val effort: Int,
        @Json(name = "stat")
        val stat: Stat
    ) {
        @JsonClass(generateAdapter = true)
        data class Stat(
            @Json(name = "name")
            val name: String,
            @Json(name = "url")
            val url: String
        )
    }
}