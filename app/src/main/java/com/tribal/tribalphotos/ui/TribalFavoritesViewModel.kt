package com.tribal.tribalphotos.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tribal.tribalphotos.model.Favorite
import com.tribal.tribalphotos.repository.TribalPhotosRepository
import com.tribal.tribalphotos.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class TribalFavoritesViewModel (
    private val tribalPhotosRepository: TribalPhotosRepository
): BaseViewModel() {
    val favoritesLiveData = MutableLiveData<List<Favorite?>>()
    lateinit var favoritesArrayList: List<Favorite?>

    fun getUserInfo() =
        viewModelScope.launch {
            getUserInfoAsync()
        }

    private suspend fun  getUserInfoAsync() {
        val result = runCatching {
            showLoading()
            tribalPhotosRepository.getFavorites()
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
                    Log.e("TAGTAG", it.toString())
                }
            }
        }
    }

}