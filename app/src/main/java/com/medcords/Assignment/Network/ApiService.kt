package com.medcords.Assignment.Network

import com.geekysingh.core.architecture.domain.entity.TodoEntity
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @Headers("Content-Type: application/json")
    @POST("/mhc/test_handle_click")

    fun getTotoDetails(
        @Body objects: JsonObject
    ): Call<TodoEntity?>?
}