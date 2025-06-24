package com.example.dipantau.model

/**
 * Kelas generik untuk menangani berbagai status data API (loading, success, error)
 */
sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val message: String) : Resource<Nothing>()
    object Loading : Resource<Nothing>()

    companion object {
        fun <T> success(data: T): Resource<T> = Success(data)
        fun error(message: String): Resource<Nothing> = Error(message)
        fun loading(): Resource<Nothing> = Loading
    }

    fun getDataOrNull(): T? = when (this) {
        is Success -> data
        else -> null
    }

    fun getErrorMessageOrNull(): String? = when (this) {
        is Error -> message
        else -> null
    }

    fun isLoading(): Boolean = this is Loading
    fun isSuccess(): Boolean = this is Success
    fun isError(): Boolean = this is Error
}
