package com.vishal2376.sugoianime.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vishal2376.sugoianime.api.AnimeAPI
import com.vishal2376.sugoianime.models.AnimeList
import com.vishal2376.sugoianime.models.AnimeRecentResponse
import com.vishal2376.sugoianime.models.AnimeRecentResponseItem
import com.vishal2376.sugoianime.models.detail.AnimeDetail
import javax.inject.Inject

class AnimeRepository @Inject constructor(private val animeAPI: AnimeAPI) {

    private val _popularAnimeLiveData = MutableLiveData<AnimeList>()
    private val _movieAnimeLiveData = MutableLiveData<AnimeList>()
    private val _animeDetailLiveData = MutableLiveData<AnimeDetail>()
    private val _recentAnimeLiveData = MutableLiveData<AnimeRecentResponse>()

    val popularAnimeLiveData: LiveData<AnimeList> get() = _popularAnimeLiveData
    val movieAnimeLiveData: LiveData<AnimeList> get() = _movieAnimeLiveData
    val animeDetailLiveData: LiveData<AnimeDetail> get() = _animeDetailLiveData
    val recentAnimeRecentResponse: LiveData<AnimeRecentResponse> get() = _recentAnimeLiveData

    suspend fun getPopularAnime() {
        val result = animeAPI.getPopularAnime()
        if (result.body() != null) {
            _popularAnimeLiveData.postValue(result.body())
        }
    }

    suspend fun getRecentAnime() {
        val result = animeAPI.getRecentAnime()
        if (result.body() != null) {
            _recentAnimeLiveData.postValue(result.body())
        }
    }

    suspend fun getMovieAnime(pageNumber: Int) {
        val result = animeAPI.getMovieAnime(pageNumber)
        if (result.body() != null) {
            _movieAnimeLiveData.postValue(result.body())
        }
    }

    suspend fun getAnimeDetail(animeID: String) {
        val result = animeAPI.getAnimeDetail(animeID)
        if (result.body() != null) {
            _animeDetailLiveData.postValue((result.body()))
        }
    }
}