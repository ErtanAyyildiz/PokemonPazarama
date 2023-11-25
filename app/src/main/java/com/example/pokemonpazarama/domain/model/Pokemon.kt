package com.example.pokemonpazarama.domain.model

import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)

data class PokemonListResponse(
    @SerializedName("results") val results: List<Pokemon>
)