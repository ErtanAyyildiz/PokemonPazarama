package com.example.pokemonpazarama.data.dto

import com.example.pokemonpazarama.domain.model.PokemonDetail
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class StatDTO(
    @SerializedName("base_stat")
    val baseStat: Int,
    @SerializedName("effort")
    val effort: Int,
    @SerializedName("stat")
    val stat: PokemonDetail.Stat.Stat
)
