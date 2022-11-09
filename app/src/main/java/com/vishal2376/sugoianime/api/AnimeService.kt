package com.vishal2376.sugoianime.api

import com.vishal2376.sugoianime.models.AnimeList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AnimeService {

    @GET("/popular")
    suspend fun getPopularAnime() :Response<AnimeList>

    @GET("/anime-movies")
    suspend fun getMovieAnime(
        @Query("page") page: Int
    ): Response<AnimeList>

}