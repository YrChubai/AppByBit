package com.example.appbybit.data.models

data class ModifAnnouncementsItem(
    val title: String,
    val description: String,
    val type: TypeAnnounce,
    val tags: List<String>,
    val url: String,
    val dateTimestamp: String,
    val startDateTimestamp: String,
    val endDateTimestamp: String,
)