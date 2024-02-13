package com.example.appbybit.data.models

data class Announcements(
    val retCode: Int,
    val retMsg: String,
    val result: ResultItem,
    val retExtInfo: ExtInfo,
    val time: Long
)

class ExtInfo(

)
