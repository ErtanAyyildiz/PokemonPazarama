package com.example.pokemonpazarama.presentation.detail.view

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.pokedex.main.dto.PokemonResponseDTO
import com.example.pokemonpazarama.R
import com.example.pokemonpazarama.data.dto.PokemonDetailDTO
import com.example.pokemonpazarama.databinding.FragmentPokemonDetailBinding
import com.example.pokemonpazarama.domain.model.PokemonDetail


class PokemonDetailFragment : AppCompatActivity() {

    private lateinit var binding: FragmentPokemonDetailBinding
    private var pokemon: PokemonResponseDTO? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentPokemonDetailBinding.inflate(layoutInflater)

        Log.i("POKEMON", "Detayları girdim")

        setContentView(binding.root)
        pokemon = intent.getSerializableExtra("POKEMON") as PokemonResponseDTO
        pokemon?.let { bindPokemonDetails(pokemon!!) }
        setupImagesCarousel()
    }

    var completeId : String? =
        when(pokemon?.id.toString().length) {
            1 -> "#00"
            2 -> "#0"
            else -> "#"
        }

    private fun bindPokemonDetails(pokemon : PokemonResponseDTO) {
        with(binding) {
            Glide.with(applicationContext).load(pokemon.sprites.other.officialArtwork.frontDefault).into(binding.pokemonImage)
            pokemonName.text = pokemon.name
            pokemonType1.text = pokemon.types[0].type.name
            pokemonId.text = completeId.plus(pokemon.id.toString())
            if(pokemon.types.size > 1) {
                pokemonType2.text = pokemon.types[1].type.name
                pokemonType2.background.setColorFilter(Color.parseColor(getString(getColorResource(pokemon?.types?.get(1)?.type?.name.toString()))), PorterDuff.Mode.SRC_OVER)
            }
            else pokemonType2.visibility = View.GONE
            pokemonWeight.text = convertPokemonMeasures(pokemon.weight).plus(" kg")
            pokemonHeight.text = convertPokemonMeasures(pokemon.height).plus(" m")
            pokemonMove1.text = pokemon.abilities[0].ability.name


            /*
            binding.hpdValue.text="%03d".format(pokemon.data?.stats?.get(0)?.base_stat)
            binding.atkValue.text="%03d".format(it.data?.stats?.get(1)?.base_stat)
            binding.defValue.text="%03d".format(it.data?.stats?.get(2)?.base_stat)
            binding.satkValue.text="%03d".format(it.data?.stats?.get(3)?.base_stat)
            binding.sdefValue.text="%03d".format(it.data?.stats?.get(4)?.base_stat)
            binding.spdValue.text="%03d".format(it.data?.stats?.get(5)?.base_stat)
            binding.pbHpBaseState.progress=it.data.stats.get(0).base_stat
            binding.pbAtkBaseState.progress=it.data.stats.get(1).base_stat
            binding.pbDefBaseState.progress=it.data.stats.get(2).base_stat
            binding.pbSatkBaseState.progress=it.data.stats.get(3).base_stat
            binding.pbSdefBaseState.progress=it.data.stats.get(4).base_stat
            binding.pbSpdBaseState.progress=it.data.stats.get(5).base_stat
            */




            if(pokemon.abilities.size > 1) pokemonMove2.text = pokemon.abilities[1].ability.name
            else pokemonMove2.visibility = View.GONE

            pokemonType1.background.setColorFilter(Color.parseColor(getString(getColorResource(pokemon?.types?.get(0)?.type?.name.toString()))), PorterDuff.Mode.SRC_OVER)

            pokemonDetails.setBackgroundColor(resources.getColor(getColorResource(pokemon?.types?.get(0)?.type?.name.toString())))
            titleAbout.setTextColor(resources.getColor(getColorResource(pokemon?.types?.get(0)?.type?.name.toString())))
            titleStatsLayout.setTextColor(resources.getColor(getColorResource(pokemon?.types?.get(0)?.type?.name.toString())))
        }
    }

    private fun setupImagesCarousel() {
        val imagesList = listOf<String>(
            pokemon?.sprites?.other?.officialArtwork?.frontDefault.toString(),
            pokemon?.sprites?.backDefault.toString(),
            pokemon?.sprites?.backFemale.toString(),
            pokemon?.sprites?.backShiny.toString(),
            pokemon?.sprites?.backShinyFemale.toString(),
            pokemon?.sprites?.frontDefault.toString(),
            pokemon?.sprites?.frontFemale.toString(),
            pokemon?.sprites?.frontShiny.toString(),
            pokemon?.sprites?.frontShinyFemale.toString()
        )
        with(binding) {
            setupImage(imagesList[0],binding.pokemonImage)
            var atualIndex = 0
            arrowRight.setOnClickListener {
                atualIndex += 1
                if(imagesList[atualIndex].isNotBlank()) setupImage(imagesList[atualIndex],binding.pokemonImage)
            }
            arrowRight.setOnClickListener {
                atualIndex -= 1
                if(imagesList[atualIndex].isNotBlank()) setupImage(imagesList[atualIndex],binding.pokemonImage)
            }
        }
    }

    private fun setupImage(url: String?, image: ImageView) {
        url.let { Glide.with(applicationContext).load(it).into(image) }
    }

    private fun convertPokemonMeasures(measure: Int?) : String {
        return measure?.toFloat()?.div(10).toString()
    }

    private fun getColorResource(type: String) : Int {
        return when(type) {
            "electric" -> R.color.electric_pokemon
            "bug" -> R.color.bug_pokemon
            "dark" -> R.color.dark_pokemon
            "dragon" -> R.color.dragon_pokemon
            "fairy" -> R.color.fairy_pokemon
            "fighting" -> R.color.fighting_pokemon
            "fire" -> R.color.fire_pokemon
            "flying" -> R.color.flying_pokemon
            "ghost" -> R.color.ghost_pokemon
            "normal" -> R.color.normal_pokemon
            "grass" -> R.color.grass_pokemon
            "ground" -> R.color.ground_pokemon
            "ice" -> R.color.ice_pokemon
            "poison" -> R.color.poison_pokemon
            "psychic" -> R.color.psychic_pokemon
            "rock" -> R.color.rock_pokemon
            "steel" -> R.color.steel_pokemon
            "water" -> R.color.water_pokemon
            else -> R.color.primary
        }
    }

}