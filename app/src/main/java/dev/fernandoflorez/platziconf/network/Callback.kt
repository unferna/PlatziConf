package dev.fernandoflorez.platziconf.network

import java.lang.Exception

interface Callback<T> {
    fun onSuccess(response: T?)
    fun onError(exception: Exception)
}