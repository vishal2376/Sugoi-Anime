package com.vishal2376.sugoianime.models.recent

data class RecentResponse(
    val currentPage: Int,
    val hasNextPage: Boolean,
    val results: List<Result>
)