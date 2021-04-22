package com.example.databindingsample.domain.base

sealed class Result<out S, out T> {
    data class Error<out E>(val error: E) : Result<E, Nothing>()
    data class Success<out V>(val value: V) : Result<Nothing, V>()

    val isError get() = this is Error<S>
    val isSuccess get() = this is Success<T>

    fun <E> error(error: E) = Error(error)
    fun <V> success(value: V) = Success(value)

     fun result(resultSuccess: (T) -> Unit, resultError: (S) -> Unit): Any =
        when (this) {
            is Success -> resultSuccess(value)
            is Error -> resultError(error)
        }
}

fun <S, T> Result<S, T>.errorResult(): S? = when (this) {
    is Result.Error<S> -> this.error
    else -> null
}

fun <S, T> Result<S, T>.successResult(): T? = when (this) {
    is Result.Success<T> -> this.value
    else -> null
}

suspend fun <S> either(executableBlock: suspend () -> S): Result<Throwable, S> = runCatching {
    Result.Success(executableBlock())
}.getOrElse { Result.Error(it) }
