package com.tribal.tribalphotos.repository

import com.tribal.tribalphotos.model.Favorite
import com.tribal.tribalphotos.model.unsplash.Photo

interface TribalPhotosRepository {

    suspend fun getPhotos(): List<Photo?>?

    suspend fun getFavorites(): List<Favorite?>
}