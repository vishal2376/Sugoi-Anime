package com.vishal2376.sugoianime.models.search

data class SearchResponseItem(
    val animeId: String,
    val animeImg: String,
    val animeTitle: String,
    val animeUrl: String,
    val status: String
)