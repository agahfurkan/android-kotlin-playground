package com.agah.furkan.androidplayground.data.web.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonResponse(
    @Json(name = "count")
    val count: Int? = 0,
    @Json(name = "next")
    val next: String? = "",
    @Json(name = "previous")
    val previous: Any? = Any(),
    @Json(name = "results")
    val results: List<Result?>? = listOf()
) {
    @JsonClass(generateAdapter = true)
    data class Result(
        @Json(name = "name")
        val name: String? = "",
        @Json(name = "url")
        val url: String? = ""
    )
}
