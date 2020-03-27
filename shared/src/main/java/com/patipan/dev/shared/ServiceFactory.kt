package com.patipan.dev.shared

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.patipan.dev.shared.service.CoinRankingService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceFactory {

    fun createCoinRankingService(): CoinRankingService {
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(createGson()))
            .client(createOkHttpClient())
            .build().create(CoinRankingService::class.java)
    }

    fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(createInterceptor())
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    private fun createInterceptor(): Interceptor {
        return Interceptor {
            val request = it.request().newBuilder().build()
            it.proceed(request)
        }
    }

    private fun createGson(): Gson {
        return GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()
    }
}