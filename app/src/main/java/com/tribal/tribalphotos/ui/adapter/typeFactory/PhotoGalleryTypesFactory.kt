package com.tribal.tribalphotos.ui.adapter.typeFactory

import com.tribal.tribalphotos.model.unsplash.Photo

interface PhotoGalleryTypesFactory :
    BaseTypesFactory {
    fun typePhotoGallery(type: Photo): Int
}
