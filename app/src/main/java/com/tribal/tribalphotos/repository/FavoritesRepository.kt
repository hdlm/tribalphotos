package com.tribal.tribalphotos.repository

import androidx.lifecycle.LiveData
import com.tribal.tribalphotos.model.Favorite

interface FavoritesRepository {
    suspend fun getAllFavorites(): List<Favorite?>?

    suspend fun insert(favorite: Favorite)
}