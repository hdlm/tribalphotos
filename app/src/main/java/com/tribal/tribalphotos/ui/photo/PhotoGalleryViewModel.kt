package com.tribal.tribalphotos.ui.photo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tribal.tribalphotos.MainActivity
import com.tribal.tribalphotos.model.Favorite
import com.tribal.tribalphotos.model.unsplash.Photo
import com.tribal.tribalphotos.repository.PhotoRepository
import com.tribal.tribalphotos.ui.adapter.itemModel.FavoriteItemModel
import com.tribal.tribalphotos.ui.adapter.itemModel.ItemModel
import com.tribal.tribalphotos.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class PhotoGalleryViewModel (
    private val photoRepository: PhotoRepository
): BaseViewModel() {

    val photosLiveData = MutableLiveData<List<Photo?>>()
    private lateinit var photosArrayList: List<Photo?>
    var userProfileSelectedLiveData = MutableLiveData<Favorite?>()

    fun getRandomPhotos() =
        viewModelScope.launch {
            getRandomPhotosAsync()
        }

    private suspend fun getRandomPhotosAsync() {
        val result = runCatching {
            showLoading()
            photoRepository.getPhotos()
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
                Log.e(MainActivity.TAG, it.toString())
                photosArrayList = ArrayList()
                photosLiveData.postValue(photosArrayList)
            }
        }
    }

}