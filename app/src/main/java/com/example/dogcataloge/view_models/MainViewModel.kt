package com.example.dogcataloge.view_models

import android.app.Application
import androidx.lifecycle.*
import com.example.dogcataloge.models.Dogs
import com.example.dogcataloge.models.DogsBreeds
import com.example.dogcataloge.network.Repository
import com.example.dogcataloge.room.getDb
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {

    private var _dogList = MutableLiveData<List<DogsBreeds>>()
    val dogList : LiveData<List<DogsBreeds>>
        get() = _dogList

    private var _image = MutableLiveData<List<Dogs>>()
    val dogs : LiveData<List<Dogs>>
        get() = _image

    private val database = getDb(application)
    private val repo = Repository(database)

    init {
        coroutines()
    }

    private fun coroutines() {
        viewModelScope.launch {
            _dogList.value = repo.getDogsList()
        }

        viewModelScope.launch{
            _image.value = repo.getImagesList()
        }
    }
}