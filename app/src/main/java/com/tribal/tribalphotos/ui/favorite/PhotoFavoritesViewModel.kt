package com.tribal.tribalphotos.ui.favorite

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tribal.tribalphotos.MainActivity
import com.tribal.tribalphotos.model.Favorite
import com.tribal.tribalphotos.repository.FavoriteRoomDatabase
import com.tribal.tribalphotos.repository.FavoritesRepository
import com.tribal.tribalphotos.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class PhotoFavoritesViewModel (
    private val favoritesRepository: FavoritesRepository
): BaseViewModel() {
    val favoritesLiveData = MutableLiveData<List<Favorite?>>()
    private lateinit var favoritesArrayList: List<Favorite?>

    fun getFavorites() =
        viewModelScope.launch {
            getFavoritesAsync()
        }

    private suspend fun  getFavoritesAsync() {
        val result = runCatching {
            showLoading()
            favoritesRepository.getAllFavorites()
        }

        with(result) {
            dismissLoading()
            onSuccess {
                it?.let{
                    favoritesArrayList = it
                    favoritesLiveData.postValue(favoritesArrayList)
                } ?: run {
                    favoritesArrayList = ArrayList()
                }
                onFailure {
                    Log.e(MainActivity.TAG, it.toString())
                }
            }
        }
    }

}