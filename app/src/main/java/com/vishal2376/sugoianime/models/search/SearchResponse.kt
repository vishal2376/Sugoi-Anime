package com.vishal2376.sugoianime.models.search

data class SearchResponse(
    val currentPage: Int,
    val hasNextPage: Boolean,
    val results: List<SearchResultList>
)