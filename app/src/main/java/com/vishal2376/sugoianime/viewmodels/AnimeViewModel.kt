package com.vishal2376.sugoianime.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vishal2376.sugoianime.models.AnimeList
import com.vishal2376.sugoianime.models.detail.AnimeDetail
import com.vishal2376.sugoianime.repository.AnimeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AnimeViewModel(private val repository: AnimeRepository) : ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getPopularAnime()
            repository.getMovieAnime(1)
        }
    }

    val popularAnime: LiveData<AnimeList> = repository.popularAnime
    val movieAnime: LiveData<AnimeList> = repository.movieAnime
    val animeDetail: LiveData<AnimeDetail> = repository.animeDetail

    suspend fun getAnimeDetail(animeID: String) {
        repository.getAnimeDetail(animeID)
    }
}