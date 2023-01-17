package com.vishal2376.sugoianime.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vishal2376.sugoianime.models.detail.AnimeDetail
import com.vishal2376.sugoianime.repository.AnimeRepository
import com.vishal2376.sugoianime.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel @Inject constructor(private val repository: AnimeRepository) : ViewModel() {

    val popularAnimeLiveData: LiveData<NetworkResult<AnimeList>> get() = repository.popularAnimeLiveData
    val movieAnimeLiveData: LiveData<NetworkResult<AnimeList>> get() = repository.movieAnimeLiveData
    val animeDetailLiveData: LiveData<NetworkResult<AnimeDetail>> get() = repository.animeDetailLiveData
    val recentAnimeLiveData: LiveData<NetworkResult<AnimeRecentResponse>> get() = repository.recentAnimeLiveData
    val searchAnimeLiveData: LiveData<NetworkResult<SearchResponse>> get() = repository.searchAnimeLiveData

    fun getPopularAnime() {
        viewModelScope.launch {
            repository.getPopularAnime()
        }
    }

    fun getRecentAnime() {
        viewModelScope.launch {
            repository.getRecentAnime()
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

    fun getAnimeSearch(query: String) {
        viewModelScope.launch {
            repository.getAnimeSearch(query)
        }
    }
}