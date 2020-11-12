package ru.startandroid.develop.financemanagment.retrofit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuilder {

    private val baseURL = "https://testrepobackend.herokuapp.com/api/"
    private val testUrl = "http://newsapi.org/v2/"

    private val authInterceptor = Interceptor { chain ->
        val newUrl = chain.request().url
            .newBuilder()
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()

        chain.proceed(newRequest)
    }
    private val client =
        OkHttpClient().newBuilder()
            .addInterceptor(authInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    private fun retrofit() =
        Retrofit.Builder()
            .client(client)
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun apiService(): ApiService {
        return retrofit().create(ApiService::class.java)
    }

}