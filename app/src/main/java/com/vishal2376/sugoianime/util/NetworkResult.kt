package com.vishal2376.sugoianime.util

sealed class NetworkResult<T>(val data: T? = null, val message: String? = null) {
    class Loading<T>() : NetworkResult<T>()
    class Success<T>(data: T) : NetworkResult<T>(data)
    class Error<T>(message: String, data: T? = null) : NetworkResult<T>(data, message)
}