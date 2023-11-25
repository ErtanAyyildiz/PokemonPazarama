package com.example.pokemonpazarama.data.api

import com.example.pokedex.main.dto.PokemonResponseDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {
    @GET("pokemon/{id}/")
    suspend fun getSinglePokemon( @Path("id") id: Int? ) : PokemonResponseDTO

}