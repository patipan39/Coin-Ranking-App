package com.patipan.dev.shared

import android.util.Log
import com.google.gson.JsonSyntaxException
import com.patipan.dev.model.ErrorApiException
import com.patipan.dev.model.ErrorException
import com.patipan.dev.model.Failed
import com.patipan.dev.model.JsonParseError
import retrofit2.Call

sealed class Either<out L, out R> {

    data class Left<out L>(val left: L) : Either<L, Nothing>()
    data class Right<out R>(val right: R) : Either<Nothing, R>()

    val isRight get() = this is Right<R>

    val isLeft get() = this is Left<L>

    fun <L> left(left: L) = Left(left)

    fun <R> right(right: R) = Right(right)


    fun fold(left: (L) -> Any, right: (R) -> Any): Any =
            when (this) {
                is Left -> (left)
                is Right -> (right)
            }
}

fun <T, R> request(call: Call<T>, transform: (T) -> R, default: T): Either<Failed, R> {
    return try {
        val response = call.execute()
        when {
            response.isSuccessful -> {
                Either.Right(transform((response.body() ?: default)))
            }
            else -> {
                Log.d("callPagingError", response.message())
                return try {
                    Either.Left(
                        ErrorApiException(response.errorBody())
                    )
                } catch (e: JsonSyntaxException) {
                    Either.Left(JsonParseError(response.code()))
                }
            }
        }
    } catch (exception: Throwable) {
        Either.Left(ErrorException(exception, -101))
    }
}