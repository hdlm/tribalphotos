package com.tribal.tribalphotos.ui.adapter.holder

import android.util.Log
import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import com.tribal.tribalphotos.MainActivity
import com.tribal.tribalphotos.R
import com.tribal.tribalphotos.ui.adapter.itemModel.ItemModel
import com.tribal.tribalphotos.ui.adapter.itemModel.PhotoGalleryItemModel
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.item_photo_row.view.*

class PhotoGalleryHolder(private val view: View) : DynamicAdapterViewHolder<PhotoGalleryItemModel>(view) {
    override fun bind(item: PhotoGalleryItemModel, position: Int, onClick: (ItemModel) -> Unit) {
        with(view) {
            tvLikes?.text = item.model.likes.toString()
            tvUsername?.text = item.model.user?.name
            imgvFavorite.setOnClickListener {
                onClick(item)
                Log.d(MainActivity.TAG, "new favorite photo: ${item.model.id}")
                imgvFavorite.setImageResource(android.R.drawable.star_on)
            }

            val imgvPicture: ImageView = findViewById<ImageView>(R.id.imgvItem)
            imgvPicture.clipToOutline = true

            // round corners
            val radius = 20
            val margin = 5
            val transformation: Transformation = RoundedCornersTransformation(radius, margin)
            Picasso.get().load(item.model.urls?.regular).transform(transformation).into(imgvPicture)

        }


    }


}
