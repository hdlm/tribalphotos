package com.tribal.tribalphotos.repository

import android.util.Log
import com.tribal.tribalphotos.MainActivity
import com.tribal.tribalphotos.model.Favorite
import org.koin.core.KoinComponent

class FavoriteRepositoryImpl(private val favoriteDao: FavoriteDao) : FavoriteRepository, KoinComponent {

    private var allFavorites : List<Favorite>? = favoriteDao.getAllFavorites()

    override suspend fun insert(favorite: Favorite) {
        Log.d(MainActivity.TAG, "${this.javaClass.simpleName} - inserting Favorite: ${favorite.username}")
        favoriteDao.insert(favorite)
    }

    override suspend fun delete(favorite: Favorite) {
        Log.d(MainActivity.TAG, "${this.javaClass.simpleName} - deleting Favorite: ${favorite.username}")
        favoriteDao. delete(favorite)
    }

    override suspend fun getFavoritesByUsername(username: String): List<Favorite>? {
        return allFavorites?.let {
            it.filter { favorite -> favorite.username.equals(username, true) }
        } ?: run {
            null
        }
    }

    override suspend fun getAllFavorites(): List<Favorite>? {
        allFavorites = favoriteDao?.getAllFavorites()
        return allFavorites
    }
}

