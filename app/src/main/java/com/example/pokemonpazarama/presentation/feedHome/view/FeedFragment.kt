package com.example.pokemonpazarama.presentation.feedHome.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonpazarama.R
import com.example.pokemonpazarama.adapter.RecyclerViewAdapter
import com.example.pokemonpazarama.databinding.FragmentFeedBinding
import com.example.pokemonpazarama.domain.model.Pokemon
import com.example.pokemonpazarama.presentation.feedHome.viewmodel.PokemonFeedViewModel
import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil


class FeedFragment : AppCompatActivity() {

    private lateinit var binding: FragmentFeedBinding
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var viewModel : PokemonFeedViewModel

    private var isSearchModeOn : Boolean = false
        set(value) {
            with(binding) {
                if(value) exitSearchIcon.visibility = View.VISIBLE
                else {
                    exitSearchIcon.visibility = View.GONE
                    recyclerView.requestFocus()
                    Log.i("POKEMON", "Geri dönüşüm odağına girildi")
                    UIUtil.hideKeyboard(this@FeedFragment, searchViewQuery)
                    searchViewQuery.setText("")
                }
                field = value
            }
        }

    // TODO use layer list or gradient to implement inner shadow
    // TODO implement load on activity create
    /// TODO implement pagination
    /// TODO fix recyclerview cards loading interruption
    /// TODO replace searchbar with text input edittext to deal with focus bug

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentFeedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
        setupObserve()
        setupRecyclerView()
        setupListeners(null)
        setupPokemonList()
    }

    override fun onPause() {
        super.onPause()
        Log.i("POKEMON", "Ana duraklamaya girildi")
        isSearchModeOn = false
    }


    @SuppressLint("ClickableViewAccessibility")
    private fun setupListeners(view: View?) {
        with(binding) {
            searchViewQuery.setOnTouchListener { view, motionEvent ->
                isSearchModeOn = true
                false }
            /// TODO replace no results warning to handle with loading pokemons
            searchViewQuery.doOnTextChanged { text, start, before, count ->
                if (!viewModel.filterPokemonList(searchViewQuery.text.toString(), recyclerViewAdapter)) {
                    Log.i("POKEMON", "Detayları girildi")
                    Toast.makeText(this@FeedFragment, "Pokemon kartı yok.!", Toast.LENGTH_SHORT).show()
                }
            }
            searchBar.setOnClickListener { isSearchModeOn = true }
            exitSearchIcon.setOnClickListener { isSearchModeOn = false }
        }
    }


    private fun setupViewModel() {
        viewModel = ViewModelProvider(this)[PokemonFeedViewModel::class.java]
    }

    private fun setupPokemonList() {
        if(viewModel.pokemonData.isEmpty()) viewModel.getPokemon()
    }

    private fun setupObserve() {
        viewModel.pokemon.observe(this@FeedFragment) { pokemon ->
            Log.i("POKEMON", "${pokemon.name}")
            recyclerViewAdapter.notifyItemInserted(viewModel.pokemonData.indexOf(pokemon))
        }
    }

    private fun setupRecyclerView() {
        with(binding) {
            recyclerViewAdapter = RecyclerViewAdapter(viewModel.pokemonData)
            var gridlayout = GridLayoutManager(this@FeedFragment, 3)
            binding.recyclerView.apply {
                setHasFixedSize(false)
                adapter = recyclerViewAdapter
                layoutManager = gridlayout
            }
            recyclerView.requestFocus()
        }
    }

}