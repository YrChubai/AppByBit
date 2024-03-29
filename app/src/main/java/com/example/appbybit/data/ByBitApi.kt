package com.example.appbybit.data

import com.example.appbybit.data.models.Announcements
import retrofit2.http.GET
import retrofit2.http.Query

interface ByBitApi {
    @GET("announcements/index")
    suspend fun getAnnouncementsData(
        @Query("locale") locale: String,
        @Query("limit") limit: Int,
    ): Announcements
}