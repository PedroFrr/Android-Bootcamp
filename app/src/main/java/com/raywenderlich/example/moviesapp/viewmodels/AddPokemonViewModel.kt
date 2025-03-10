package com.raywenderlich.example.moviesapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.raywenderlich.example.moviesapp.repository.PokemonRepository
import com.raywenderlich.example.moviesapp.model.Pokemon
import com.raywenderlich.example.moviesapp.utils.DEFAULT_IMAGE_URL
import java.util.*

class AddPokemonViewModel(private val pokemonRepository: PokemonRepository): ViewModel() {
    var id = 0
    var name = ""
    var height = 0.0
    var weight = 0.0
    private var defaultImageUrl = DEFAULT_IMAGE_URL
    private val saveLiveData = MutableLiveData<Boolean>()

    suspend fun addPokemon(pokemon: Pokemon) = pokemonRepository.addPokemon(pokemon)

    fun getSaveLiveData(): LiveData<Boolean> = saveLiveData

    suspend fun savePokemon() {
        return if (canSavePokemon()) {
            val pokemonId = UUID.randomUUID().toString().substring(0..3).replace("[^0-9]".toRegex(), "")
            val pokemon = Pokemon(id = pokemonId.toInt(), name = name, height = height, weight = weight, imageUrl = defaultImageUrl)
            pokemonRepository.addPokemon(pokemon)
            saveLiveData.postValue(true)
        } else {
            saveLiveData.postValue(false)
        }
    }

    fun canSavePokemon(): Boolean {
        return name.isNotBlank() && height != 0.0 && weight != 0.0
    }

}