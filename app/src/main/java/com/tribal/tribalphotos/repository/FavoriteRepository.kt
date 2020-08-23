package com.tribal.tribalphotos.repository

import androidx.lifecycle.LiveData
import com.tribal.tribalphotos.model.Favorite

interface FavoriteRepository {
    suspend fun getAllFavorites(): List<Favorite>?
    suspend fun insert(favorite: Favorite)
    suspend fun delete(favorite: Favorite)
    suspend fun getFavoritesByUsername(username: String): List<Favorite>?

}