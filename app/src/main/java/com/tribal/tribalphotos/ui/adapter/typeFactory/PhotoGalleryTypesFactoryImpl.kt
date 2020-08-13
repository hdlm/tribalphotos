package com.tribal.tribalphotos.ui.adapter.typeFactory

import android.view.View
import com.tribal.tribalphotos.R
import com.tribal.tribalphotos.model.Photo
import com.tribal.tribalphotos.ui.adapter.holder.DynamicAdapterViewHolder
import com.tribal.tribalphotos.ui.adapter.holder.PhotoGalleryHolder

class PhotoGalleryTypesFactoryImpl : PhotoGalleryTypesFactory {

    override fun typePhotoGallety(type: Photo) =
        R.layout.recyclerview_item_row


    override fun holder(type: Int, view: View): DynamicAdapterViewHolder<*> {
        return when (type) {
            R.layout.recyclerview_item_row -> PhotoGalleryHolder(view)
            else -> throw RuntimeException("Illegal view type")
        }
    }
}