package com.tribal.tribalphotos.ui.adapter.typeFactory

import android.view.View
import com.tribal.tribalphotos.R
import com.tribal.tribalphotos.model.Favorite
import com.tribal.tribalphotos.ui.adapter.holder.DynamicAdapterViewHolder


class FavoriteTypesFactoryImpl : FavoriteTypesFactory {
    override fun typeFavorite(type: Favorite): Int =
        R.layout.recyclerview_item_favorite_row

    override fun holder(type: Int, view: View): DynamicAdapterViewHolder<*> {
        TODO("Not yet implemented")
    }

}

