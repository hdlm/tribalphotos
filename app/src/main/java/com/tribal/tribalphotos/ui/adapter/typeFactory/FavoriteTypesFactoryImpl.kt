package com.tribal.tribalphotos.ui.adapter.typeFactory

import android.view.View
import com.tribal.tribalphotos.R
import com.tribal.tribalphotos.model.Favorite
import com.tribal.tribalphotos.ui.adapter.holder.DynamicAdapterViewHolder
import com.tribal.tribalphotos.ui.adapter.holder.PhotoFavoriteHolder
import java.lang.RuntimeException


class FavoriteTypesFactoryImpl : FavoriteTypesFactory {
    override fun typeFavorite(type: Favorite): Int =
        R.layout.item_favorite_row

    override fun holder(type: Int, view: View): DynamicAdapterViewHolder<*> {
        return when (type) {
            R.layout.item_favorite_row -> PhotoFavoriteHolder(view)
            else -> throw RuntimeException("Illegal view type")
        }
    }

}

