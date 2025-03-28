package com.prabhanshu.animallassignment.data.network

sealed class Resource<T>(
    val message: String? = null,
    val data: T? = null,
    val isLoading: Boolean? = false
) {
    class Loading<T>(state: Boolean) : Resource<T>(isLoading = state)
    class Success<T>(data: T?) : Resource<T>(data = data)
    class Error<T>(message: String?) : Resource<T>(message = message)
}