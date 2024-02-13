package com.example.appbybit.data

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create



const val API_LIMIT = 10
const val API_LOCALE = "en-US"
fun adapterAnnouncementsApi2() : ByBitApi {
    val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    val gson: Gson = GsonBuilder().create()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.bybit.com/v5/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()
    return retrofit.create<ByBitApi>()
}