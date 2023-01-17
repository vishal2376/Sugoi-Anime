package com.vishal2376.sugoianime.api

import com.vishal2376.sugoianime.models.info.InfoResponse
import com.vishal2376.sugoianime.models.watch.WatchResponse
import com.vishal2376.sugoianime.models.search.SearchResponse
import com.vishal2376.sugoianime.models.top.TopResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeAPI {

    @GET("/anime/gogoanime/{query}")
    suspend fun getSearchAnime(
        @Path("query") query: String,
        @Query("page") page: Int = 1
    ): Response<SearchResponse>

    @GET("/anime/gogoanime/info/{id}")
    suspend fun getAnimeInfo(
        @Path("id") id: String
    ): Response<InfoResponse>

    @GET("/anime/gogoanime/top-airing")
    suspend fun getTopAnime(
        @Query("page") page: Int = 1
    ): Response<TopResponse>


    @GET("/anime/gogoanime/recent-episodes")
    suspend fun getRecentEpisodes(
        @Query("type") type: Int = 1, //1 - Japanese with SUB , 2 - English Dub without SUB
        @Query("page") page: Int = 1
    ): Response<TopResponse>


    @GET("/anime/gogoanime/watch/{episodeId}")
    suspend fun getStreamLink(
        @Path("episodeId") episodeId: String,
        @Query("server") server: String = "gogocdn" // Available servers - "gogocdn" , "steamsb"
    ): Response<WatchResponse>

    @GET("/anime/gogoanime/servers/{episodeId}")
    suspend fun getAvailableServer(
        @Path("episodeId") episodeId: String
    ): Response<SearchResponse>

}