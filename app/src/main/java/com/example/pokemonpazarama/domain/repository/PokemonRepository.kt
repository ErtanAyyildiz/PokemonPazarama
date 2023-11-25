package com.example.pokemonpazarama.domain.repository

import com.example.pokedex.main.dto.PokemonResponseDTO
import com.example.pokemonpazarama.domain.model.Pokemon
import com.example.pokemonpazarama.domain.model.PokemonDetail
import com.example.pokemonpazarama.util.Resource
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonRepository {

    suspend fun getPokemonList(): Resource<List<Pokemon>>
    suspend fun getPokemonDetail(pokemonName: String): Resource<PokemonDetail>

}