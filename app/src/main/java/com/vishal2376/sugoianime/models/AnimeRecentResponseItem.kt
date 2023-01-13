package com.vishal2376.sugoianime.models

data class AnimeRecentResponseItem(
    val animeId: String,
    val animeImg: String,
    val animeTitle: String,
    val episodeId: String,
    val episodeNum: String,
    val episodeUrl: String,
    val subOrDub: String
)