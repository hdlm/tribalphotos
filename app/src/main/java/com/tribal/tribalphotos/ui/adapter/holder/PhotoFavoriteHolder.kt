package com.tribal.tribalphotos.ui.adapter.holder

import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import com.tribal.tribalphotos.R
import com.tribal.tribalphotos.ui.adapter.itemModel.FavoriteItemModel
import com.tribal.tribalphotos.ui.adapter.itemModel.ItemModel
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.item_favorite_row.view.*

class PhotoFavoriteHolder(private val view: View) : DynamicAdapterViewHolder<FavoriteItemModel>(view) {
    override fun bind(item: FavoriteItemModel, position: Int, onClick: (ItemModel) -> Unit) {
        val favorite = item.model
        with(view) {
            btnRemoveFavorite.setOnClickListener {
                //PENDING remove favorite
            }
            val imgvPicture = findViewById<ImageView>(R.id.imgvItem)
            imgvPicture.clipToOutline = true

            // round corners
            val radius = 20
            val margin = 5
            val transformation: Transformation = RoundedCornersTransformation(radius, margin)
            Picasso.get().load(favorite.profileImage).transform(transformation).into(imgvPicture)
            //PENDING cargar la imagen del user profile al hacer clic sobre la foto
        }

    }
}