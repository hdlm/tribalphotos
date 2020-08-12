package com.tribal.tribalphotos.repository

import com.tribal.tribalphotos.model.Photo

interface TribalPhotosRepository {

    suspend fun getPhotos(): List<Photo?>?
    fun giveHello(): String
}