package com.example.appbybit.data.models

import java.sql.Timestamp

data class Announcements(
    val retCode: Int,
    val retMsg: String,
    val result: ResultItem,
    val retExtInfo: ExtInfo,
    val time: Timestamp
)

class ExtInfo(

)

