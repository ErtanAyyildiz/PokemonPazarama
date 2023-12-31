package com.example.pokedex.main.dto

import com.example.pokemonpazarama.data.dto.StatDTO
import com.example.pokemonpazarama.domain.model.PokemonDetail
import com.google.gson.annotations.SerializedName // Retrofit recurse to indicate a JSON serialization
import java.io.Serializable

data class PokemonResponseDTO(
    @SerializedName("id")
    var id : Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("height")
    val height: Int?,
    @SerializedName("weight")
    val weight: Int?,
    @SerializedName("sprites")
    val sprites: SpritesDTO,
    @SerializedName("types")
    val types: List<TypesDTO>,
    @SerializedName("abilities")
    val abilities: List<AbilitiesDTO>,
    @SerializedName("flavor_text")
    val flavor_text: String,

    ) : Serializable

