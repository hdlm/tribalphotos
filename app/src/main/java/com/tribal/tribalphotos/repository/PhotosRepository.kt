package com.tribal.tribalphotos.repository

import com.tribal.tribalphotos.model.unsplash.Photo

interface PhotosRepository {

    suspend fun getPhotos(): List<Photo?>?

}