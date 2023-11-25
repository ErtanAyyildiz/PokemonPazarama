package com.example.pokemonpazarama.data.repository

import com.example.pokemonpazarama.data.service.PokemonApi
import com.example.pokemonpazarama.domain.model.Pokemon
import com.example.pokemonpazarama.domain.model.PokemonDetail
import com.example.pokemonpazarama.domain.repository.PokemonRepository
import com.example.pokemonpazarama.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class PokemonRepositoryImpl @Inject constructor(val api: PokemonApi):PokemonRepository{
    override suspend fun getPokemonList(): Resource<List<Pokemon>> {
        return try {
            val response = api.getPokemonList()
            if (response?.isSuccessful==true) {
                response.body()?.let { resultResponse ->
                    Resource.success(resultResponse.results)
                } ?: Resource.error("An unknown error occurred", null)
            } else {
                Resource.error("Response Error", null)
            }
        } catch (e: Exception) {
            Resource.error("An unknown error occurred", null)
        }
    }

    override suspend fun getPokemonDetail(pokemonName: String): Resource<PokemonDetail> {
        TODO("Not yet implemented")
    }


}