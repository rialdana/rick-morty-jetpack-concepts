package com.example.rickmorty.utils

/**
 * This interface allows us to have a generic function to set any type of
 * data into an adapter, so we can take advantage of binding adapters.
 */
interface GenericAdapter<T> {
    fun setData(data: T)
}