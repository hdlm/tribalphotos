package com.tribal.tribalphotos.ui.photo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tribal.tribalphotos.MainActivity
import com.tribal.tribalphotos.model.Favorite
import com.tribal.tribalphotos.model.unsplash.Photo
import com.tribal.tribalphotos.repository.FavoritesRepository
import com.tribal.tribalphotos.repository.PhotosRepository
import com.tribal.tribalphotos.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PhotoGalleryViewModel (
    private val photosRepository: PhotosRepository,
    private val favoriteRepository: FavoritesRepository
): BaseViewModel() {

    val photosLiveData = MutableLiveData<List<Photo?>>()
    private lateinit var photosArrayList: List<Photo?>

    fun addFavorite(favorite: Favorite) =
        viewModelScope.launch(Dispatchers.IO) {
            addFavoriteAsync(favorite)
        }

    private suspend fun addFavoriteAsync(favorite: Favorite): Unit = favoriteRepository.insert(favorite)


    fun getRandomPhotos() =
        viewModelScope.launch {
            getRandomPhotosAsync()
        }

    private suspend fun getRandomPhotosAsync() {
        val result = runCatching {
            showLoading()
            photosRepository.getPhotos()
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
            }
        }
    }

}