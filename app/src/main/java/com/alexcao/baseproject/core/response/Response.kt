package com.alexcao.baseproject.core.response

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed class Response<out T> {
    data class Success<out T>(val data: T) : Response<T>()
    data class Error(val exception: Throwable) : Response<Nothing>()
    data object Loading : Response<Nothing>()
}

fun <T> Flow<T>.asResponse(): Flow<Response<T>> = map<T, Response<T>> { Response.Success(it) }
    .onStart { emit(Response.Loading) }
    .catch { emit(Response.Error(it)) }