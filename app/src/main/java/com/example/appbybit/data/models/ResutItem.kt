package com.example.appbybit.data.models

import java.sql.Timestamp

data class ResultItem(
    val total: Int,
    val list: List<AnnounceItem>
)

data class AnnounceItem(
    val title: String,
    val description: String,
    val type: TypeAnnounce,
    val tags: List<String>,
    val url: String,
    val dateTimestamp: Timestamp,
    val startDateTimestamp: Timestamp,
    val endDateTimestamp: Timestamp,
)
data class TypeAnnounce(
    val title: String,
    val key: String,
)
