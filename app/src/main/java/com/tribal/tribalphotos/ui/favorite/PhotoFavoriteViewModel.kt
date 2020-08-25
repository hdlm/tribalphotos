package com.tribal.tribalphotos.ui.favorite

import android.content.Context
import android.content.res.Resources
import android.util.Log
import android.widget.ArrayAdapter
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PhotoFavoriteViewModel ( ): BaseViewModel() {

    val favoritesLiveData = MutableLiveData<List<Favorite?>>()
    private var favoritesArrayList: List<Favorite?> = ArrayList()
    private lateinit var favoriteRepository: FavoriteRepository
    val adapterLiveData = MutableLiveData<ArrayAdapter<String?>>()

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

    fun addFavorite(favorite: Favorite) =
        viewModelScope.launch(Dispatchers.IO) {
            addFavoriteAsync(favorite)
        }

    private suspend fun addFavoriteAsync(favorite: Favorite) = withContext(Dispatchers.IO) {
        favoriteRepository.insert(favorite)
        var lstNew = favoritesArrayList.toMutableList()
        lstNew.add(favorite)
        favoritesLiveData.postValue(lstNew.toList())
    }

    fun delete(favorite: Favorite) =
        viewModelScope.launch {
            getDeleteAsync(favorite)
        }

    private suspend fun getDeleteAsync(favorite: Favorite) = withContext(Dispatchers.IO) {
        favoriteRepository.delete(favorite)
        (favoritesArrayList as ArrayList).remove(favorite)
        favoritesLiveData.postValue(favoritesArrayList)
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