package com.tribal.tribalphotos.repository

import com.tribal.tribalphotos.model.unsplash.Photo

interface PhotoRepository {

    suspend fun getPhotos(): List<Photo?>?

}