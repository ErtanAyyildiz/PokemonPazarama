package com.example.pokemonpazarama.data.dto

import com.example.pokemonpazarama.domain.model.PokemonDetail
import com.google.gson.annotations.SerializedName

data class PokemonDetailDTO(
    val abilities: List<PokemonDetail.Ability>,
    val baseExperience: Int,
    val forms: List<PokemonDetail.Form>,
    val gameİndices: List<PokemonDetail.Gameİndice>,
    val height: Int,
    val heldİtems: List<Any>,
    val id: Int,
    val isDefault: Boolean,
    val locationAreaEncounters: String,
    val moves: List<PokemonDetail.Move>,
    val name: String,
    val order: Int,
    val pastAbilities: List<Any>,
    val pastTypes: List<Any>,
    val species: PokemonDetail.Species,
    val sprites: PokemonDetail.Sprites,
    val stats: List<PokemonDetail.Stat>,
    val types: List<PokemonDetail.Type>,
    val weight: Int
)
