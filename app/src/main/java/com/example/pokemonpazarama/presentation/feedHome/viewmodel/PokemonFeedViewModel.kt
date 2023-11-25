package com.example.pokemonpazarama.presentation.feedHome.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.main.dto.PokemonResponseDTO
import com.example.pokemonpazarama.adapter.RecyclerViewAdapter
import com.example.pokemonpazarama.data.api.PokemonRepoApi
import com.example.pokemonpazarama.domain.model.Pokemon
import com.example.pokemonpazarama.domain.repository.PokemonRepository
import com.example.pokemonpazarama.util.Resource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val MIN_POKEMON_ID = 1
private const val MAX_POKEMON_ID = 151

class PokemonFeedViewModel :ViewModel() {
    private val repository = PokemonRepoApi()

    private val _pokemon = MutableLiveData<PokemonResponseDTO>()
    val pokemon: LiveData<PokemonResponseDTO> = _pokemon

    var pokemonData : MutableList<PokemonResponseDTO> = mutableListOf()
        private set

    fun getPokemon() {
        viewModelScope.launch() {
            try {
                for (i in MIN_POKEMON_ID..MAX_POKEMON_ID) {
                    val pokemon = repository.getSinglePokemon(i)
                    pokemonData.add(pokemon)

                    withContext(Dispatchers.Main) {
                        _pokemon.value = pokemon
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun filterPokemonList(query: String?, recyclerViewAdapter: RecyclerViewAdapter) : Boolean {
        val filteredlist: ArrayList<PokemonResponseDTO> = ArrayList()

        try {
            pokemonData.forEach { item ->
                if (item.name.contains(query!!, ignoreCase = true) || item.id.toString().contains(query)) {
                    filteredlist.add(item)
                }
            }
        } catch (Exception: Exception) {
            return true
        }

        return if(filteredlist.isEmpty() && !query?.isEmpty()!!) false
        else {
            recyclerViewAdapter.filterList(filteredlist)
            true
        }
    }

}