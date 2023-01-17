package com.vishal2376.sugoianime.models.streamlink

data class StreamLinkResponse(
    val headers: Headers,
    val sources: List<Source>
)