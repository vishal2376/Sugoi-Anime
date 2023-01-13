package com.vishal2376.sugoianime.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vishal2376.sugoianime.models.AnimeList
import com.vishal2376.sugoianime.models.detail.AnimeDetail
import com.vishal2376.sugoianime.repository.AnimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel @Inject constructor(private val repository: AnimeRepository) : ViewModel() {

    val popularAnime: LiveData<AnimeList> get() = repository.popularAnime
    val movieAnime: LiveData<AnimeList> get() = repository.movieAnime
    val animeDetail: LiveData<AnimeDetail> get() = repository.animeDetail

    fun getPopularAnime() {
        viewModelScope.launch {
            repository.getPopularAnime()
        }
    }

    fun getMovieAnime(pageNumber: Int) {
        viewModelScope.launch {
            repository.getMovieAnime(pageNumber)
        }
    }

    fun getAnimeDetail(animeID: String) {
        viewModelScope.launch {
            repository.getAnimeDetail(animeID)
        }
    }
}