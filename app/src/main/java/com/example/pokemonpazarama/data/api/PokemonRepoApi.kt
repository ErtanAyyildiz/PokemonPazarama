package com.example.pokemonpazarama.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonRepoApi {
    private val service: PokemonService = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PokemonService::class.java)

    suspend fun getSinglePokemon(id: Int) = service.getSinglePokemon(id)
}