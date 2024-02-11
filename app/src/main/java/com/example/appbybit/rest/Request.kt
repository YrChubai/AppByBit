package com.example.appbybit.rest

import com.example.appbybit.data.ByBitApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


const val API_LIMIT = 10
const val API_LOCALE = "en-US"
private fun requestAnnounceData(city: String) {
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
    val byBitApi = retrofit.create<ByBitApi>()

    CoroutineScope(Dispatchers.IO).launch {
        val call = byBitApi.getAnnouncementsData(
            locale = API_LOCALE,
            limit = API_LIMIT,
        )
    }

}