package com.tribal.tribalphotos.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tribal.tribalphotos.model.Photo
import com.tribal.tribalphotos.repository.TribalPhotosRepository
import com.tribal.tribalphotos.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class TribalPhotosViewModel(
    private val tribalPhotosRepository: TribalPhotosRepository
): BaseViewModel() {

    val photosLiveData = MutableLiveData<List<Photo?>>()
    lateinit var photosArrayList: List<Photo?>
    val cadenaPrueba: String = "HOLA"


    fun getRandomPhotos() =
        viewModelScope.launch {
            getRandomPhotosAsync()
        }

    private suspend fun getRandomPhotosAsync() {
        val result = runCatching {
            showLoading()
            tribalPhotosRepository.getPhotos()
        }

        with(result) {
            dismissLoading()
            onSuccess {
                it?.let {
                    photosArrayList = it
                    photosLiveData.postValue(photosArrayList)
                } ?: run {
                    photosArrayList = ArrayList()
                }
            }
            onFailure {
                Log.e("TAGTAG", it.toString())
            }
        }
    }


    fun sayHello() = "todo fino ${tribalPhotosRepository.giveHello()} fron $this"
}