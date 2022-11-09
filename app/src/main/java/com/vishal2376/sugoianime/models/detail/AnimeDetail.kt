package com.vishal2376.sugoianime.models.detail

data class AnimeDetail(
    val animeImg: String,
    val animeTitle: String,
    val episodesList: List<Episodes>,
    val genres: List<String>,
    val otherNames: String,
    val releasedDate: String,
    val status: String,
    val synopsis: String,
    val totalEpisodes: String,
    val type: String
)