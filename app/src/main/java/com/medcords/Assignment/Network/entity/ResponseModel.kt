package com.geekysingh.core.architecture.domain.entity

import com.google.gson.annotations.SerializedName

data class TodoEntity(
    @SerializedName("status") val id: Int,
    @SerializedName("msg")  val title: String,
    @SerializedName("errCode")  val userId: Int,
    @SerializedName("data") val data : Data

)
data class Data (

    @SerializedName("rstr") val rstr : String
)