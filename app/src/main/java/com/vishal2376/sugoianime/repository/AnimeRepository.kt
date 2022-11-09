package com.vishal2376.sugoianime.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vishal2376.sugoianime.api.AnimeService
import com.vishal2376.sugoianime.models.AnimeList
import com.vishal2376.sugoianime.models.detail.AnimeDetail

class AnimeRepository(private val animeService: AnimeService) {

    private val _popularAnime = MutableLiveData<AnimeList>()
    private val _movieAnime = MutableLiveData<AnimeList>()
    private val _animeDetail = MutableLiveData<AnimeDetail>()

    val popularAnime: LiveData<AnimeList> get() = _popularAnime
    val movieAnime: LiveData<AnimeList> get() = _movieAnime
    val animeDetail: LiveData<AnimeDetail> get() = _animeDetail

    suspend fun getPopularAnime(){
        val result = animeService.getPopularAnime()
        if(result.body() != null){
            _popularAnime.postValue(result.body())
        }
    }

    suspend fun getMovieAnime(pageNumber: Int) {
        val result = animeService.getMovieAnime(pageNumber)
        if (result.body() != null) {
            _movieAnime.postValue(result.body())
        }
    }

    suspend fun getAnimeDetail(animeID: String) {
        val result = animeService.getAnimeDetail(animeID)
        if (result.body() != null) {
            _animeDetail.postValue((result.body()))
        }
    }
}