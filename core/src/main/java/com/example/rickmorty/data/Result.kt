package com.example.rickmorty.data

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */

sealed class Result<out R> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
    object Loading : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            Loading -> "Loading"
        }
    }
}

/**
 * `true` if [Result] is of type [Success] & holds non-null [Success.data].
 */
val Result<*>.succeeded
    get() = this is Result.Success && data != null

inline fun <reified T> Result<T>.getData(): T {
    return (this as Result.Success<T>).data
}

inline fun <reified T> Result<T>.getError(): Exception {
    return (this as Result.Error).exception
}