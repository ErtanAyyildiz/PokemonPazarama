package com.example.pokemonpazarama.presentation.detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonpazarama.domain.model.Pokemon
import com.example.pokemonpazarama.domain.model.PokemonDetail
import com.example.pokemonpazarama.domain.repository.PokemonRepository
import com.example.pokemonpazarama.util.Resource
import com.example.pokemonpazarama.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListDetailViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
): ViewModel() {

    private var loadingJob: Job? = null

    private val mutableMovieDetail = MutableLiveData<PokemonDetail?>()

    private val mutableIsLoading = MutableLiveData<Boolean>()

    private val mutableError = MutableLiveData<String?>()



/*
    fun loadData(pokemonList: List<Pokemon>){
        mutableIsLoading.value=true
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val result = pokemonRepository.getPokemonList()
            when(result.status){
                Status.SUCCESS. -> {
                    mutableIsLoading.value = false
                    mutableMovieDetail.value = result.data
                    if(result.data == null){
                        mutableError.value = "Unexpected error"
                    }
                }
                Status.ERROR -> {
                    mutableError.value = result.message
                    mutableIsLoading.value = false
                }
                Status.LOADING -> {
                    mutableIsLoading.value = true
                }
            }
        }
    }



 */

}
