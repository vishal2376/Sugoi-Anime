package com.vishal2376.sugoianime.repository

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vishal2376.sugoianime.api.AnimeAPI
import com.vishal2376.sugoianime.models.AnimeList
import com.vishal2376.sugoianime.models.AnimeRecentResponse
import com.vishal2376.sugoianime.models.detail.AnimeDetail
import com.vishal2376.sugoianime.util.NetworkResult
import javax.inject.Inject

class AnimeRepository @Inject constructor(private val animeAPI: AnimeAPI) {

    private val _popularAnimeLiveData = MutableLiveData<NetworkResult<AnimeList>>()
    private val _movieAnimeLiveData = MutableLiveData<NetworkResult<AnimeList>>()
    private val _animeDetailLiveData = MutableLiveData<NetworkResult<AnimeDetail>>()
    private val _recentAnimeLiveData = MutableLiveData<NetworkResult<AnimeRecentResponse>>()

    val popularAnimeLiveData: LiveData<NetworkResult<AnimeList>> get() = _popularAnimeLiveData
    val movieAnimeLiveData: LiveData<NetworkResult<AnimeList>> get() = _movieAnimeLiveData
    val animeDetailLiveData: LiveData<NetworkResult<AnimeDetail>> get() = _animeDetailLiveData
    val recentAnimeLiveData: LiveData<NetworkResult<AnimeRecentResponse>> get() = _recentAnimeLiveData

    suspend fun getPopularAnime() {
        _popularAnimeLiveData.postValue(NetworkResult.Loading())
        val response = animeAPI.getPopularAnime()
        if (response.isSuccessful && response.body() != null) {
            _popularAnimeLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else {
            _popularAnimeLiveData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }

    suspend fun getRecentAnime() {
        _recentAnimeLiveData.postValue(NetworkResult.Loading())
        val response = animeAPI.getRecentAnime()
        if (response.body() != null) {
            _recentAnimeLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else {
            _recentAnimeLiveData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }

    suspend fun getMovieAnime(pageNumber: Int) {
        _movieAnimeLiveData.postValue(NetworkResult.Loading())
        val response = animeAPI.getMovieAnime(pageNumber)
        if (response.body() != null) {
            _movieAnimeLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else {
            _movieAnimeLiveData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }

    suspend fun getAnimeDetail(animeID: String) {
        _animeDetailLiveData.postValue(NetworkResult.Loading())
        val response = animeAPI.getAnimeDetail(animeID)
        if (response.body() != null) {
            _animeDetailLiveData.postValue((NetworkResult.Success(response.body()!!)))
        } else {
            _animeDetailLiveData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }
}