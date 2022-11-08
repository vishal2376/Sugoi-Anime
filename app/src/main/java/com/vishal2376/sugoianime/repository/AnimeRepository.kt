package com.vishal2376.sugoianime.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vishal2376.sugoianime.api.AnimeService
import com.vishal2376.sugoianime.models.AnimeList

class AnimeRepository(private val animeService: AnimeService) {

    private val _popularAnime = MutableLiveData<AnimeList>()
    private val _movieAnime = MutableLiveData<AnimeList>()

    val popularAnime: LiveData<AnimeList> get() = _popularAnime
    val movieAnime: LiveData<AnimeList> get() = _movieAnime

    suspend fun getPopularAnime(){
        val result = animeService.getPopularAnime()
        if(result.body() != null){
            _popularAnime.postValue(result.body())
        }
    }

    suspend fun getMovieAnime(){
        val result = animeService.getMovieAnime()
        if(result.body() != null){
            _movieAnime.postValue(result.body())
        }
    }
}