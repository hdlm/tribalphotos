package com.tribal.tribalphotos.ui.adapter.holder

import android.view.View
import com.squareup.picasso.Picasso
import com.tribal.tribalphotos.ui.adapter.itemModel.ItemModel
import com.tribal.tribalphotos.ui.adapter.itemModel.PhotoGalleryItemModel
import kotlinx.android.synthetic.main.recyclerview_item_row.view.*

class PhotoGalleryHolder(private val view: View) : DynamicAdapterViewHolder<PhotoGalleryItemModel>(view) {
    override fun bind(item: PhotoGalleryItemModel, position: Int, onClick: (ItemModel) -> Unit) {
        with(view) {

            tvLikes?.text = item.model.likes.toString()
            tvUsername?.text = item.model.user?.name

            Picasso.get().load(item.model.urls?.full).into(view.imgvItem)
        }

    }

}
