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

    val photos = MutableLiveData<List<Photo?>>()

    fun getPhotos() =
        viewModelScope.launch {
            getPhotosAsync()
        }

    private suspend fun getPhotosAsync() {
        val result = runCatching {
            showLoading()
            tribalPhotosRepository.getPhotos()
        }

        with(result) {
            dismissLoading()
            onSuccess {
                it?.let {
                    photos.postValue(it)
                }
            }
            onFailure {
                Log.e("TAGTAG", it.toString())
            }
        }
    }
}