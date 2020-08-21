package com.tribal.tribalphotos.repository

import androidx.lifecycle.LiveData
import com.tribal.tribalphotos.model.Favorite

class FavoriteRepositoryImpl(private val favoriteDao: FavoriteDao) : FavoritesRepository {

    private val allFavorites : List<Favorite?>? = favoriteDao.getAllFavorites()

    override suspend fun getAllFavorites(): List<Favorite?>? {
        return allFavorites
    }

    override suspend fun insert(favorite: Favorite) {
        favoriteDao.insert(favorite)
    }
}