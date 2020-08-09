package com.tribal.tribalphotos.repository

import com.tribal.tribalphotos.model.Photo
import org.koin.core.KoinComponent

class TribalPhotosRepositoryImpl : TribalPhotosRepository, KoinComponent {

    //TODO llamados a los endpoints
    override suspend fun getPhotos(): List<Photo?>? {
        TODO("Not yet implemented")
    }
}