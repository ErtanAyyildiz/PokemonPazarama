package com.example.pokemonpazarama.data.service

import com.example.pokedex.main.dto.PokemonResponseDTO
import com.example.pokemonpazarama.domain.model.PokemonDetail
import com.example.pokemonpazarama.domain.model.PokemonListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {

    @GET("pokemon/{id}/")
    suspend fun getSinglePokemon( @Path("id") id: Int? ) : PokemonResponseDTO

    @GET("pokemon?limit=1000&offset=0")
    suspend fun getPokemonList(
    ):Response<PokemonListResponse>

    @GET("pokemon/{name}")
    suspend fun getPokemonDetail(
        @Path("name") name: String
    ):Response<PokemonDetail>
}