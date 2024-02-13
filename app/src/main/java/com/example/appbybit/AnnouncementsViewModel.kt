package com.example.appbybit

import androidx.lifecycle.ViewModel
import com.example.appbybit.data.adapterAnnouncementsApi2
import com.example.appbybit.data.models.AnnounceItem
import kotlinx.coroutines.launch
import android.icu.text.SimpleDateFormat
import android.icu.util.TimeZone
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.appbybit.data.API_LIMIT
import com.example.appbybit.data.API_LOCALE
import com.example.appbybit.data.models.ModifAnnouncementsItem
import java.sql.Date
import java.util.Locale

class AnnouncementsViewModel(): ViewModel()
    {
        var api = adapterAnnouncementsApi2()
        var liveDataList = mutableStateOf<List<ModifAnnouncementsItem>>(listOf())
        var dataLoaded = mutableStateOf("start")
    init{
        loadData()
        Log.d("TagStarted","Start")
    }
    fun loadData(){
        viewModelScope.launch {
            try {
            val call = api.getAnnouncementsData(
                locale = API_LOCALE,
                limit = API_LIMIT,
            )
            formateRespone(call.result.list)
            dataLoaded.value = "loaded"
                Log.d("TagStart","$dataLoaded")
            } catch (e: Exception){
                dataLoaded.value = "error"
            }
        }
    }
    fun formateRespone(responsetItem: List<AnnounceItem>){
        val announcementsModif =
            responsetItem.mapIndexed { _, item ->
                ModifAnnouncementsItem(
                    item.title,
                    item.description,
                    item.type,
                    item.tags,
                    item.url,
                    formateDate(item.dateTimestamp),
                    formateDate(item.startDateTimestamp),
                    formateDate(item.endDateTimestamp)
            )
        }
        liveDataList.value = announcementsModif
    }
    fun formateDate(startDateTimestamp: Long): String {
        val dateFormat = SimpleDateFormat("MMM d, yyyy, hh:mm a 'UTC'", Locale.US)
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
        val startDate = dateFormat.format(Date(startDateTimestamp))
        return startDate
    }
}

