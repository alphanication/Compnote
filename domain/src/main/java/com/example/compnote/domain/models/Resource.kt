package com.example.compnote.domain.models

sealed class Resource<out T> {
    class Loading<out T> : Resource<T>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Fail<out T>(val e: Exception) : Resource<T>()
}