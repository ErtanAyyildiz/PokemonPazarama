package com.example.pokemonpazarama.adapter

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedex.main.dto.PokemonResponseDTO
import com.example.pokemonpazarama.databinding.ItemPokemonBinding
import com.example.pokemonpazarama.presentation.detail.view.PokemonDetailFragment

class RecyclerViewViewHolder(private var binding: ItemPokemonBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(pokemon : PokemonResponseDTO) {
        var completeId : String? =
            when(pokemon.id.toString().length) {
                1 -> "#00"
                2 -> "#0"
                else -> "#"
            }

        with(binding) {
            Glide.with(itemView.context).load(pokemon.sprites.other.officialArtwork.frontDefault).into(binding.pokemonIcon)
            pokemonName.text = pokemon.name
            pokemonCode.text = completeId.plus(pokemon.id.toString())
        }
        setupListeners(pokemon)
    }

    private fun setupListeners(pokemon : PokemonResponseDTO) {
        with(binding) {
            root.setOnClickListener {
                val intent = Intent(it.context, PokemonDetailFragment::class.java)
                intent.putExtra("POKEMON", pokemon)
                it.context.startActivity(intent)
            }
        }
    }
}