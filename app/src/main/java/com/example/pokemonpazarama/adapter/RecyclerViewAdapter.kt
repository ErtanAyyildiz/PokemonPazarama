package com.example.pokemonpazarama.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.main.dto.PokemonResponseDTO
import com.example.pokemonpazarama.databinding.ItemPokemonBinding


class RecyclerViewAdapter(
    private var pokemonList: MutableList<PokemonResponseDTO>,
) : RecyclerView.Adapter<RecyclerViewViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerViewViewHolder {
        val binding = ItemPokemonBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return RecyclerViewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewViewHolder, position: Int) {
        holder.bind(pokemonList[position])
    }

    override fun getItemCount() = pokemonList.size

    fun filterList(filterlist: ArrayList<PokemonResponseDTO>) {
        pokemonList = filterlist
        notifyDataSetChanged()
    }

}
