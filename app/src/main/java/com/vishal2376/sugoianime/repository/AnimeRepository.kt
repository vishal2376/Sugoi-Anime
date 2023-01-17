package com.vishal2376.sugoianime.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vishal2376.sugoianime.api.AnimeAPI
import com.vishal2376.sugoianime.models.info.InfoResponse
import com.vishal2376.sugoianime.models.recent.RecentResponse
import com.vishal2376.sugoianime.models.search.SearchResponse
import com.vishal2376.sugoianime.models.streamlink.StreamLinkResponse
import com.vishal2376.sugoianime.models.top.TopResponse
import com.vishal2376.sugoianime.util.NetworkResult
import javax.inject.Inject

class AnimeRepository @Inject constructor(private val animeAPI: AnimeAPI) {

    private val _searchAnimeLiveData = MutableLiveData<NetworkResult<SearchResponse>>()
    private val _animeInfoLiveData = MutableLiveData<NetworkResult<InfoResponse>>()
    private val _topAnimeLiveData = MutableLiveData<NetworkResult<TopResponse>>()
    private val _recentEpisodesLiveData = MutableLiveData<NetworkResult<RecentResponse>>()
    private val _streamLinkLiveData = MutableLiveData<NetworkResult<StreamLinkResponse>>()

    val searchAnimeLiveData: LiveData<NetworkResult<SearchResponse>> get() = _searchAnimeLiveData
    val animeInfoLiveData: LiveData<NetworkResult<InfoResponse>> get() = _animeInfoLiveData
    val topAnimeLiveData: LiveData<NetworkResult<TopResponse>> get() = _topAnimeLiveData
    val recentEpisodesLiveData: LiveData<NetworkResult<RecentResponse>> get() = _recentEpisodesLiveData
    val streamLinkLiveData: LiveData<NetworkResult<StreamLinkResponse>> get() = _streamLinkLiveData

    suspend fun getSearchAnime(query: String) {
        _searchAnimeLiveData.postValue(NetworkResult.Loading())
        val response = animeAPI.getSearchAnime(query)
        if (response.isSuccessful && response.body() != null) {
            _searchAnimeLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else {
            _searchAnimeLiveData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }

    suspend fun getAnimeInfo(id: String) {
        _animeInfoLiveData.postValue(NetworkResult.Loading())
        val response = animeAPI.getAnimeInfo(id)
        if (response.isSuccessful && response.body() != null) {
            _animeInfoLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else {
            _animeInfoLiveData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }

    suspend fun getTopAnime() {
        _topAnimeLiveData.postValue(NetworkResult.Loading())
        val response = animeAPI.getTopAnime()
        if (response.isSuccessful && response.body() != null) {
            _topAnimeLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else {
            _topAnimeLiveData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }

    suspend fun getRecentEpisodes() {
        _recentEpisodesLiveData.postValue(NetworkResult.Loading())
        val response = animeAPI.getRecentEpisodes()
        if (response.isSuccessful && response.body() != null) {
            _recentEpisodesLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else {
            _recentEpisodesLiveData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }

    suspend fun getStreamLink(episodeId: String) {
        _streamLinkLiveData.postValue(NetworkResult.Loading())
        val response = animeAPI.getStreamLink(episodeId)
        if (response.isSuccessful && response.body() != null) {
            _streamLinkLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else {
            _streamLinkLiveData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }

}