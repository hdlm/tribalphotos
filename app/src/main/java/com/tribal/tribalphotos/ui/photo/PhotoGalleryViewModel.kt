package com.tribal.tribalphotos.ui.photo

import android.content.Context
import android.content.res.Resources
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tribal.tribalphotos.MainActivity
import com.tribal.tribalphotos.R
import com.tribal.tribalphotos.model.Favorite
import com.tribal.tribalphotos.model.unsplash.Photo
import com.tribal.tribalphotos.repository.FavoriteRepository
import com.tribal.tribalphotos.repository.FavoriteRepositoryImpl
import com.tribal.tribalphotos.repository.FavoriteRoomDatabase
import com.tribal.tribalphotos.repository.PhotoRepository
import com.tribal.tribalphotos.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PhotoGalleryViewModel (
    private val photoRepository: PhotoRepository
): BaseViewModel() {

    private lateinit var favoriteRepository: FavoriteRepository
    val photosLiveData = MutableLiveData<List<Photo?>>()
    private lateinit var photosArrayList: List<Photo?>

    fun prepareLocalDatabase(context: Context) =
        viewModelScope.launch {
            prepareLocalDatabaseAsync(context)
        }

    private suspend fun prepareLocalDatabaseAsync(context: Context) {
        val result = kotlin.runCatching {
            showLoading()
            val db = FavoriteRoomDatabase.getDatabase(context)
            val dao = db!!.favoriteDao()
            favoriteRepository = FavoriteRepositoryImpl(dao)
            favoriteRepository.getAllFavorites()
        }
        with(result) {
            dismissLoading()
            onSuccess {
                Log.d(MainActivity.TAG, "Database prepare")
            }
            onFailure {
                Log.d(MainActivity.TAG, it.toString())
                Toast.makeText(context, Resources.getSystem().getString(R.string.error_msg_create_database),
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

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
            }
        }
    }

}