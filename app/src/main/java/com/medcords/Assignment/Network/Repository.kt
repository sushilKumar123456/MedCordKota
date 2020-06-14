package com.medcords.Assignment.Network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.geekysingh.core.architecture.domain.entity.Data
import com.geekysingh.core.architecture.domain.entity.TodoEntity
import com.google.gson.JsonObject
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call

import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Repository {
    private val apiService: ApiService
    var postParam =  JsonObject()

    private val volumesResponseLiveData: MutableLiveData<Data?>
    fun searchVolumes(
        keyword: String?

    ) {
        postParam.addProperty("bname",""+keyword) ;
        apiService.getTotoDetails(postParam)?.enqueue(object : Callback<TodoEntity?> {
            override fun onResponse(
                call: Call<TodoEntity?>?,
                response: Response<TodoEntity?>
            ) {
                if (response.body() != null) {
                    volumesResponseLiveData.postValue(response.body()!!.data)
                }
            }

            override fun onFailure(call: Call<TodoEntity?>?, t: Throwable?) {
                volumesResponseLiveData.postValue(null)
            }
        })
    }

    fun getVolumesResponseLiveData(): LiveData<Data?> {
        return volumesResponseLiveData
    }

    companion object {
        private const val BOOK_SEARCH_SERVICE_BASE_URL = "http://qa-doctor.medcords.com/"
    }

    init {
        volumesResponseLiveData = MutableLiveData<Data?>()
        val interceptor = HttpLoggingInterceptor()
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        apiService = Retrofit.Builder()
            .baseUrl(BOOK_SEARCH_SERVICE_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}