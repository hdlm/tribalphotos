package com.tribal.tribalphotos.ui.adapter.itemModel

import com.tribal.tribalphotos.model.unsplash.Photo
import com.tribal.tribalphotos.ui.adapter.typeFactory.BaseTypesFactory
import com.tribal.tribalphotos.ui.adapter.typeFactory.PhotoGalleryTypesFactory


class PhotoGalleryItemModel(val model: Photo) : ItemModel() {
    override fun type(typesFactory: BaseTypesFactory): Int {
        return (typesFactory as PhotoGalleryTypesFactory).typePhotoGallety(model)
    }
}
