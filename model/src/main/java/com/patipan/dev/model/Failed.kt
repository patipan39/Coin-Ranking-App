package com.patipan.dev.model

import com.google.gson.annotations.SerializedName
import okhttp3.ResponseBody

sealed class Failed(val throwable: Throwable? = null)

data class FailedError(
    @SerializedName("status") val errorCode: Int? = null,
    @SerializedName("message") val message: String? = null
)

data class ErrorApiException(val errorResponse: ResponseBody? = null) : Failed()
data class JsonParseError(val code: Int? = null) : Failed()
data class ErrorException(val error: Throwable? = null, val code: Int? = null) : Failed(error)

