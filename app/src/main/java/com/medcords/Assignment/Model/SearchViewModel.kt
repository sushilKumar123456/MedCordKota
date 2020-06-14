package com.medcords.Assignment.Model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.geekysingh.core.architecture.domain.entity.Data
import com.medcords.Assignment.Network.Repository


class SearchViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: Repository? = null
    private lateinit var responseLiveData: LiveData<Data?>
    fun init() {
        repository = Repository()
        responseLiveData = repository!!.getVolumesResponseLiveData()
    }

    fun searchVolumes(keyword: String?) {
        repository?.searchVolumes(keyword)
    }

    fun getVolumesResponseLiveData(): LiveData<Data?> {
        return responseLiveData
    }
}