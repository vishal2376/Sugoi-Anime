package com.vishal2376.sugoianime.api

import com.vishal2376.sugoianime.models.AnimeList
import com.vishal2376.sugoianime.models.detail.AnimeDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeAPI {

    @GET("/popular")
    suspend fun getPopularAnime(): Response<AnimeList>

    @GET("/anime-movies")
    suspend fun getMovieAnime(
        @Query("page") page: Int
    ): Response<AnimeList>

    @GET("/anime-details/{animeID}")
    suspend fun getAnimeDetail(
        @Path("animeID") animeID: String
    ): Response<AnimeDetail>
}