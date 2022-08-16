package com.esaudev.networkgarden.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esaudev.networkgarden.data.local.MoviesProvider
import com.esaudev.networkgarden.domain.model.MovieCatalog
import com.esaudev.networkgarden.domain.model.Pokemon
import com.esaudev.networkgarden.domain.usecase.GetAllPokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllPokemonUseCase: GetAllPokemonUseCase
): ViewModel() {

    private val _catalogList : MutableLiveData<List<MovieCatalog>> = MutableLiveData()
    val catalogList : LiveData<List<MovieCatalog>>
        get() = _catalogList

    fun getMoviesCatalog() {
        viewModelScope.launch {
            _catalogList.value = MoviesProvider.movieCatalog
        }
    }

}