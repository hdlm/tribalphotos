package com.tribal.tribalphotos.ui.adapter.holder

import android.util.Log
import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import com.tribal.tribalphotos.MainActivity
import com.tribal.tribalphotos.R
import com.tribal.tribalphotos.ui.adapter.itemModel.FavoriteItemModel
import com.tribal.tribalphotos.ui.adapter.itemModel.ItemModel
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.item_favorite_row.view.*

class PhotoFavoriteHolder(private val view: View) : DynamicAdapterViewHolder<FavoriteItemModel>(view) {
    override fun bind(item: FavoriteItemModel, position: Int, onClick: (ItemModel) -> Unit, onClickFavorite: (ItemModel) -> Unit) {
        val favorite = item.model
        with(view) {
            btnRemoveFavorite.setOnClickListener {
                Log.d(MainActivity.TAG, "${this.javaClass.simpleName} - click to delete favorite photo: ${item.model.id}")
                onClickFavorite(item)
            }
            val imgvPicture = findViewById<ImageView>(R.id.imgvItem)
            imgvPicture.setOnClickListener {
                onClick(item)
            }
            imgvPicture.clipToOutline = true

            // round corners
            val radius = 20
            val margin = 5
            val transformation: Transformation = RoundedCornersTransformation(radius, margin)
            Picasso.get().load(favorite.linkImage).transform(transformation).into(imgvPicture)
        }

    }
}