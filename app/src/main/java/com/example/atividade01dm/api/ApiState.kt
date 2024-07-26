package com.example.atividade01dm.api


sealed class ApiState<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Created<T> :ApiState<T>()
    class Loading<T>:ApiState<T>()
    class Success<T>(data: T): ApiState<T>(
        data = data
    )
    class Error<T>(message: String): ApiState<T>(
        message = message
    )
}