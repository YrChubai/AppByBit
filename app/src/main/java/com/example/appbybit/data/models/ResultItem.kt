package com.example.appbybit.data.models

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
    val dateTimestamp: Long,
    val startDateTimestamp: Long,
    val endDateTimestamp: Long,
)
data class TypeAnnounce(
    val title: String,
    val key: String,
)
