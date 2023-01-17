package com.vishal2376.sugoianime.models.top

data class TopResponse(
    val currentPage: Int,
    val hasNextPage: Boolean,
    val results: List<Result>
)