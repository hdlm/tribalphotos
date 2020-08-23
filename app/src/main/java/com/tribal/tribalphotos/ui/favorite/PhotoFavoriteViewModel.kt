package com.tribal.tribalphotos.ui.favorite

import android.content.Context
import android.content.res.Resources
import android.provider.Settings.Global.getString
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tribal.tribalphotos.MainActivity
import com.tribal.tribalphotos.R
import com.tribal.tribalphotos.model.Favorite
import com.tribal.tribalphotos.repository.FavoriteRepository
import com.tribal.tribalphotos.repository.FavoriteRepositoryImpl
import com.tribal.tribalphotos.repository.FavoriteRoomDatabase
import com.tribal.tribalphotos.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class PhotoFavoriteViewModel ( ): BaseViewModel() {

    private lateinit var favoriteRepository: FavoriteRepository
    val favoritesLiveData = MutableLiveData<List<Favorite?>>()
    private var favoritesArrayList: List<Favorite?> = ArrayList()

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
                it?.let {
                    favoritesArrayList = it
                    favoritesLiveData.postValue(favoritesArrayList)
                } ?: run {
                    favoritesArrayList = ArrayList()
                }
            }
            onFailure {
                Log.d(MainActivity.TAG, it.toString())
                Toast.makeText(context, Resources.getSystem().getString(R.string.error_msg_create_database),Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun getFavorites() =
        viewModelScope.launch {
            getFavoritesAsync()
        }

    private suspend fun  getFavoritesAsync() {
        val result = runCatching {
            showLoading()
            favoriteRepository.getAllFavorites()
        }
        with(result) {
            dismissLoading()
            onSuccess {
                it?.let {
                    favoritesArrayList = it
                    favoritesLiveData.postValue(favoritesArrayList)
                } ?: run {
                    favoritesArrayList = ArrayList()
                    favoritesLiveData.postValue(favoritesArrayList)
                }
            }
            onFailure {
                Log.e(MainActivity.TAG, it.toString())
            }
        }
    }

}