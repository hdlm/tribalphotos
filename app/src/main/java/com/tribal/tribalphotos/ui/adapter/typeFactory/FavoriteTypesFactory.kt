package com.tribal.tribalphotos.ui.adapter.typeFactory

import com.tribal.tribalphotos.model.Favorite

interface FavoriteTypesFactory :
    BaseTypesFactory {
    fun typeFavorite(type: Favorite): Int
}
