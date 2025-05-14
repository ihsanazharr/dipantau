package com.example.dipantau.model


/**
 * Kelas generik untuk menangani berbagai status data API (loading, success, error)
 */
sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val message: String) : Resource<Nothing>()

    /**
     * Status loading tanpa data
     */
    object Loading : Resource<Nothing>()

    companion object {
        /**
         * Fungsi helper untuk membuat Success
         */
        fun <T> success(data: T): Resource<T> = Success(data)

        /**
         * Fungsi helper untuk membuat Error
         */
        fun error(message: String): Resource<Nothing> = Error(message)

        /**
         * Fungsi helper untuk membuat Loading
         */
        fun loading(): Resource<Nothing> = Loading
    }

    /**
     * Fungsi untuk mengakses data jika status Success
     */
    fun getDataOrNull(): T? = when (this) {
        is Success -> data
        else -> null
    }

    /**
     * Fungsi untuk mengakses pesan error jika status Error
     */
    fun getErrorMessageOrNull(): String? = when (this) {
        is Error -> message
        else -> null
    }

    /**
     * Fungsi untuk mengecek apakah status Loading
     */
    fun isLoading(): Boolean = this is Loading

    /**
     * Fungsi untuk mengecek apakah status Success
     */
    fun isSuccess(): Boolean = this is Success

    /**
     * Fungsi untuk mengecek apakah status Error
     */
    fun isError(): Boolean = this is Error
}