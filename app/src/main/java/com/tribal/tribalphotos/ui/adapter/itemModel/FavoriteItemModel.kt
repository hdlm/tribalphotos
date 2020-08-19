package com.tribal.tribalphotos.ui.adapter.itemModel

import com.tribal.tribalphotos.model.Favorite
import com.tribal.tribalphotos.ui.adapter.typeFactory.BaseTypesFactory
import com.tribal.tribalphotos.ui.adapter.typeFactory.FavoriteTypesFactory

class FavoriteItemModel (val model: Favorite) : ItemModel() {
    override fun type(typesFactory: BaseTypesFactory): Int {
        return (typesFactory as FavoriteTypesFactory).typeFavorite(model)
    }
}